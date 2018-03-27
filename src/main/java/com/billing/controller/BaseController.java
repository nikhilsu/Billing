package com.billing.controller;

import com.billing.helper.Constants;
import com.billing.helper.Response;
import com.billing.model.User;
import com.billing.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class BaseController {
    protected final UserService userService;

    protected BaseController(UserService userService) {
        this.userService = userService;
    }

    protected boolean currentUserAdmin(HttpSession session) throws Exception {
        int userId = Integer.valueOf(session.getAttribute(Constants.SessionKeys.LOGGED_IN_USER).toString());
        Response<User> userById = userService.getUserById(userId);
        return userById.isSuccessful() && userById.data().isAdmin();
    }

    boolean anyParameterNullOrEmpty(List<String> parameters) {
        return parameters.stream().anyMatch(parameter -> parameter == null || parameter.isEmpty());
    }
}
