package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.BillCategory;

public interface BillCategoryDao {
    Response<Integer> save(BillCategory billCategory);
    Response deleteById(int billCategoryId);
    Response update(BillCategory billCategory);
    Response<BillCategory> findById(int id);
}
