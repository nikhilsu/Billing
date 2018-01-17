package com.billing.service.impl;

import com.billing.dao.BillDao;
import com.billing.helper.Response;
import com.billing.model.Bill;
import com.billing.model.BillCategory;
import com.billing.model.Patient;
import com.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class BillServiceImpl implements BillService {
    private final BillDao billDao;

    @Autowired
    public BillServiceImpl(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    public Response<List<Bill>> getAll() {
        return billDao.findAll();
    }

    @Override
    public Response createBill(Patient patient, List<BillCategory> billCategories) {
        Timestamp timeNowInUTC = new Timestamp(Date.from(Instant.now()).getTime());
        Bill bill = new Bill().setBillCategories(billCategories)
                              .setPatient(patient)
                              .setCreatedOn(timeNowInUTC);
        return billDao.save(bill);
    }
}
