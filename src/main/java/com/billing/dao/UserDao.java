package com.billing.dao;

import com.billing.helper.Response;
import com.billing.model.User;

public interface UserDao {
    Response<Integer> save(User user);
    Response update(User user);
    Response<User> findByUserId(String userId) throws Exception;
    Response<User> findById(int userId);
}
