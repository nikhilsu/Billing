package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.Bill;

public interface BillDao {
    Response<Integer> save(Bill bill);
}
