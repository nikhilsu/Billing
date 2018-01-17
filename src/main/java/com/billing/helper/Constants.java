package com.billing.helper;

public class Constants {
    public static class SessionKeys {
        public static final String LOGGED_IN_USER = "loggedInUser";
    }

    public static class ModelAttributes {
        public static final String MESSAGE = "message";
        public static final String RESULT = "result";
        public static final String UPDATE = "update";
    }

    public static class Route {
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String ROOT = "/";
        public static final String REDIRECT = "redirect:";
        public static final String REGISTER = "/register";
        public static final String BILL_CATEGORY = "/bill-category";
        public static final String UPDATE_BILL_CATEGORY = "/bill-category/{id}";
    }

    public static class RedirectPage {
        public static final String INDEX = "index";
        public static final String LOGIN_FORM = "login";
        public static final String REGISTRATION_FORM = "register";
        public static final String BILL_CATEGORY_FORM = "billCategory";
    }
}
