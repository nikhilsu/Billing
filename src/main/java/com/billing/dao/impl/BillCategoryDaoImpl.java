package com.billing.dao.impl;

import com.billing.dao.BillCategoryDao;
import com.billing.helper.Response;
import com.billing.model.BillCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillCategoryDaoImpl extends BaseDaoImpl implements BillCategoryDao{

    @Autowired
    public BillCategoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(BillCategory billCategory) {
        return super.save(billCategory);
    }

    @Override
    public Response deleteById(int billCategoryId) {
        return super.delete(getCurrentSession().get(BillCategory.class, billCategoryId));
    }

    @Override
    public Response update(BillCategory billCategory) {
        return super.update(billCategory);
    }

    @Override
    public Response<BillCategory> findById(int id) {
        return Response.Success(getCurrentSession().get(BillCategory.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response<List<BillCategory>> findAll() {
        return Response.Success(getCurrentSession().createQuery("from BillCategory").list());
    }
}
