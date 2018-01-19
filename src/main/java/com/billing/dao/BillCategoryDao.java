package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.BillCategory;

import java.util.List;

public interface BillCategoryDao {
    Response<Integer> save(BillCategory billCategory);
    Response update(BillCategory billCategory);
    Response<BillCategory> findById(int id);
    Response<BillCategory> findByName(String name, boolean ignoreCase);
    Response<List<BillCategory>> findAll();
}
