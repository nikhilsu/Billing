package com.billing.dao.impl;

import com.billing.dao.PatientDao;
import com.billing.helper.Response;
import com.billing.model.Patient;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientDaoImpl extends BaseDaoImpl implements PatientDao {

    @Autowired
    public PatientDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(Patient patient) {
        return super.save(patient);
    }

    @Override
    public Response update(Patient patient) {
        return super.update(patient);
    }

    @Override
    public Response<Patient> findByPhoneNumber(String phoneNumber) {
        Query query = getCurrentSession().createQuery("from Patient where phoneNumber = :phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);
        try {
            return Response.Success((Patient) query.getSingleResult());
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }
}
