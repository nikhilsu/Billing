package com.billing.helper;

public class Masker {
    private static final int MASK_VALUE = 1000;

    public static String maskDbId(int id) {
        return Integer.toString(id + MASK_VALUE);
    }

    public static String maskDbId(String id) {
        return Integer.toString(Integer.parseInt(id) + MASK_VALUE);
    }
}
