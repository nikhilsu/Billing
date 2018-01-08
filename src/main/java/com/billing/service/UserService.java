package com.billing.service;

import com.billing.helper.Response;

public interface UserService {
    Response<Integer> createUser(String firstName, String lastName, String userId, String password) throws Exception;
    Response<Integer> loginUser(String userId, String password) throws Exception;
}
