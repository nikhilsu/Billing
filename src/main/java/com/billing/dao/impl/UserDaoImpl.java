package com.billing.dao.impl;

import com.billing.dao.UserDao;
import com.billing.helper.Response;
import com.billing.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(User user) {
        return super.save(user);
    }

    @Override
    public Response<User> findByUserId(String userId) throws Exception {
        Query query = getCurrentSession().createQuery("from User where userId = :userId");
        query.setParameter("userId", userId);
        try {
            return Response.Success((User) query.getSingleResult());
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }

    @Override
    public Response<User> findById(int userId) {
        try {
            return Response.Success(getCurrentSession().get(User.class, userId));
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }
}
