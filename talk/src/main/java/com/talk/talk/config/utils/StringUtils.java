package com.talk.talk.config.utils;

public class StringUtils {

    public static boolean isStringEmptyOrNull(String value) {
        return value == null || "".equals(value) || value.length() == 0;
    }
}
