package com.billing.helper;

public class Constants {
    public static class SessionKeys {
        public static final String LOGGED_IN_USER = "loggedInUser";
    }

    public static class ModelAttributes {
        public static final String MESSAGE = "message";
    }

    public static class Route {
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String ROOT = "/";
    }

    public static class RedirectPage {
        public static final String INDEX = "index";
        public static final String LOGIN_FORM = "login";
    }
}
