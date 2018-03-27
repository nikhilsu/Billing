package com.billing.service;

import com.billing.helper.Response;
import com.billing.model.User;
import com.billing.model.UserRole;

public interface UserService {
    Response<Integer> createUser(String firstName, String lastName, String userId, String password, UserRole userRole) throws Exception;
    Response<Integer> loginUser(String userId, String password) throws Exception;
    Response changePassword(int userId, String newPassword) throws Exception;
    Response<User> getUserById(int userId) throws Exception;
}
