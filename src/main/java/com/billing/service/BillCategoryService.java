package com.billing.service;

import com.billing.helper.Response;
import com.billing.model.BillCategory;
import com.billing.model.CategoryType;

public interface BillCategoryService {
    Response createBillCategory(String name, float cost, CategoryType type);
    Response<BillCategory> getById(int id);
    Response updateBillCategory(int billCategoryId, String name, float cost, CategoryType type) throws Exception;
}
