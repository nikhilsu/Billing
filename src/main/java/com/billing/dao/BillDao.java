package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.Bill;

import java.sql.Timestamp;
import java.util.List;

public interface BillDao {
    Response<Integer> save(Bill bill);
    Response<List<Bill>> findAll();
    Response<List<Bill>> findDateRange(Timestamp start, Timestamp end);
    Response<Bill> findBillById(int billId);
}
