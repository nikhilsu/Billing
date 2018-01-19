package com.billing.service;

import com.billing.helper.Response;
import com.billing.model.BillCategory;
import com.billing.model.CategoryType;

import java.util.List;

public interface BillCategoryService {
    Response createBillCategory(String name, double cost, CategoryType type) throws Exception;
    Response<BillCategory> getById(int id);
    Response updateBillCategory(int billCategoryId, String name, double cost, CategoryType type) throws Exception;
    Response<List<BillCategory>> getAll();
}
