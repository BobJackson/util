package com.wangyousong.util;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailCheckerTest  {

    @Test
    public void testIsValidEmail() {
        boolean validEmail = new EmailChecker("").isValidEmail();
        assertFalse(validEmail);

        validEmail = new EmailChecker(null).isValidEmail();
        assertFalse(validEmail);

        validEmail = new EmailChecker("null").isValidEmail();
        assertFalse(validEmail);

        validEmail = new EmailChecker("     ").isValidEmail();
        assertFalse(validEmail);

        validEmail = new EmailChecker("111@qq.com").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("111@qq.com").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("1187688895@qq.com").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("hfuucmms@163.com").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("wys2317@hotmail.com").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("15255148690@139.com").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("15255148690@gmail.com").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("152551@yahoo.com.cn").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("15255148690@msn.com").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("15255148690@ITCCOLP.COM.HK").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("15255148690@SEED.NET.TW").isValidEmail();
        assertTrue(validEmail);

        validEmail = new EmailChecker("yousong.wang@highsoft.ltd").isValidEmail();
        assertTrue(validEmail);
    }
}