package com.billing.dao.impl;

import com.billing.helper.Response;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseDaoImpl {
    private SessionFactory sessionFactory;

    public BaseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    Response<Integer> save(Object model) {
        try {
            Integer serialId = (Integer) getCurrentSession().save(model);
            return Response.Success(serialId);
        } catch (HibernateException exception) {
            // TODO: Log exception
            System.out.println(exception.getMessage());
            return Response.Failure(exception.getMessage());
        }
    }

    Response update(Object model) {
        try {
            Session currentSession = getCurrentSession();
            currentSession.update(model);
            currentSession.flush();
            return Response.SuccessEmptyPayload();
        } catch (HibernateException exception) {
            return Response.Failure(exception.getMessage());
        }
    }

    Response delete(Object model) {
        try {
            Session currentSession = getCurrentSession();
            currentSession.delete(model);
            currentSession.flush();
            return Response.SuccessEmptyPayload();
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }

    Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
