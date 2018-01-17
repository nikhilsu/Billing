package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.BillCategory;

import java.util.List;

public interface BillCategoryDao {
    Response<Integer> save(BillCategory billCategory);
    Response deleteById(int billCategoryId);
    Response update(BillCategory billCategory);
    Response<BillCategory> findById(int id);
    Response<List<BillCategory>> findAll();
}
