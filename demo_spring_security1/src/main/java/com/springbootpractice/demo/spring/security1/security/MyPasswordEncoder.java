package com.springbootpractice.demo.spring.security1.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * 说明：加密和验证密码
 * @author carter
 * 创建时间： 2020年02月08日 4:14 下午
 **/
@Component
public class MyPasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence rawPassword) {
        //双层md5加密
        return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()).getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(encodedPassword,encode(rawPassword));
    }
}
