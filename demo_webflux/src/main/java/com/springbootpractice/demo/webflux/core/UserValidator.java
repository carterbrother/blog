package com.springbootpractice.demo.webflux.core;


import com.springbootpractice.demo.webflux.dao.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月16日 9:48 上午
 **/

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        if (Objects.equals(user.getUserName(),"xxx")){
            errors.rejectValue("userName","","用户名不能为xxx");
        }

    }
}
