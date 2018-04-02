package com.billing.dao.impl;

import com.billing.dao.BillDao;
import com.billing.helper.Response;
import com.billing.model.Bill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
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
    public Response<List<Bill>> findAll() {
        try {
            return Response.Success(getCurrentSession().createQuery("FROM Bill").list());
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }

    @Override
    public Response<List<Bill>> findDateRange(Timestamp startDate, Timestamp endDate) {
        try {
            return Response.Success(getCurrentSession().createQuery("FROM Bill WHERE createdOn BETWEEN :startDate AND :endDate ")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList());
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }

    @Override
    public Response<Bill> findBillById(int billId) {
        try {
            Bill result = getCurrentSession().get(Bill.class, billId);
            return result == null ? Response.Failure("No element found") : Response.Success(result);
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }
}
