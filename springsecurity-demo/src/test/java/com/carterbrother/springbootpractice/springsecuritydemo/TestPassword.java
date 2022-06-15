package com.carterbrother.springbootpractice.springsecuritydemo;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年04月10日 11:28 AM
 * @Copyright (c) carterbrother
 */
public class TestPassword {


    @Test
    public void test1()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("123456"));

    }
}
