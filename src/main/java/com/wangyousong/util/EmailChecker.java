package com.wangyousong.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author Bob
 * @version 1.0
 * @date 2020/9/13 20:48
 */
public class EmailChecker {
    private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private final String email;

    public EmailChecker(String email) {
        this.email = email;
    }

    public boolean isValidEmail() {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
}