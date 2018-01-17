package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.Bill;

import java.util.List;

public interface BillDao {
    Response<Integer> save(Bill bill);
    Response<List<Bill>> findAll();
}
