package com.billing.helper;

public class Constants {
    public static class SessionKeys {
        public static final String LOGGED_IN_USER = "loggedInUser";
    }

    public static class ModelAttributes {
        public static final String MESSAGE = "message";
        public static final String TEST_CATEGORY_MESSAGE = "testCategoryMessage";
        public static final String RESULT = "result";
        public static final String IS_ADMIN = "isAdmin";
        public static final String IS_ERROR = "isError";
    }

    public static class Route {
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String ROOT = "/";
        public static final String REDIRECT = "redirect:";
        public static final String REGISTER = "/register";
        public static final String BILL_CATEGORY = "/bill-category";
        public static final String UPDATE_BILL_CATEGORY = "/bill-category/{id}";
        public static final String BILL = "/bill";
        public static final String NEW_BILL = "/new-bill";
        public static final String PATIENT = "/patient";
        public static final String NEW_PATIENT_FORM = "/new-patient";

        public static String NEW_BILL(int patientId) {
            return NEW_BILL + "?patientId=" + patientId;
        }
    }

    public static class RedirectPage {
        public static final String INDEX = "index";
        public static final String LOGIN_FORM = "login";
        public static final String REGISTRATION_FORM = "register";
        public static final String BILL_CATEGORIES = "billCategories";
        public static final String BILL_CREATE_FORM = "newBill";
        public static final String NEW_PATIENT_FORM = "newPatientForm";
        public static final String PATIENT = "patient";
        public static final String BILL = "bill";
    }
}
