package com.billing.service;

import com.billing.helper.Response;
import com.billing.model.Bill;
import com.billing.model.BillCategory;
import com.billing.model.Patient;

import java.util.List;

public interface BillService {
    Response<List<Bill>> getAll();
    Response createBill(Patient patient, List<BillCategory> billCategories);
    Response<List<Bill>> getByDateRange(String startDate, String endDate) throws Exception;
}
