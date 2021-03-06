package com.billing.service.impl;

import com.billing.dao.BillCategoryDao;
import com.billing.helper.Response;
import com.billing.helper.validator.ModelValidator;
import com.billing.model.BillCategory;
import com.billing.model.CategoryType;
import com.billing.service.BillCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class BillCategoryServiceImpl implements BillCategoryService {

    private BillCategoryDao billCategoryDao;
    private ModelValidator modelValidator;

    @Autowired
    public BillCategoryServiceImpl(BillCategoryDao billCategoryDao, ModelValidator modelValidator) {
        this.billCategoryDao = billCategoryDao;
        this.modelValidator = modelValidator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response createBillCategory(String name, double cost, CategoryType type) throws Exception {
        if(categoryOfSameNameAndTypeExists(name, type))
            return Response.Failure("Category with same name and type already exists");
        BillCategory billCategory = new BillCategory();
        billCategory.setName(name)
                    .setCost(cost)
                    .setCategoryType(type)
                    .setBills(Collections.emptyList());
        Response validationResponse = modelValidator.validate(billCategory);
        if (!validationResponse.isSuccessful()) {
            return Response.Failure(validationResponse.errors());
        }
        return billCategoryDao.save(billCategory);
    }

    @Override
    public Response<BillCategory> getById(int id) {
        return billCategoryDao.findById(id);
    }

    @Override
    public Response updateBillCategory(int billCategoryId, String name, double cost, CategoryType type) throws Exception {
        Response<BillCategory> findById = billCategoryDao.findById(billCategoryId);
        if (!findById.isSuccessful()) {
            return Response.Failure(findById.errors());
        }
        BillCategory billCategory = findById.data();
        billCategory.setName(name)
                    .setCost(cost)
                    .setCategoryType(type);
        return billCategoryDao.update(billCategory);
    }

    @Override
    public Response<List<BillCategory>> getAll() {
        return billCategoryDao.findAll();
    }

    private boolean categoryOfSameNameAndTypeExists(String name, CategoryType type) throws Exception {
        Response<List<BillCategory>> findCategoryWithSameName = billCategoryDao.findByName(name, true);
        return findCategoryWithSameName.isSuccessful() &&
               findCategoryWithSameName.data().stream().anyMatch(category -> category.getCategoryType() == type);
    }
}
