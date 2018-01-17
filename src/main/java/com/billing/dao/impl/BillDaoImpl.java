package com.billing.dao.impl;

import com.billing.dao.BillDao;
import com.billing.helper.Response;
import com.billing.model.Bill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDaoImpl extends BaseDaoImpl implements BillDao {

    @Autowired
    public BillDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(Bill bill) {
        return super.save(bill);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response<List<Bill>> findAll() {
        return Response.Success(getCurrentSession().createQuery("from Bill").list());

    }
}
