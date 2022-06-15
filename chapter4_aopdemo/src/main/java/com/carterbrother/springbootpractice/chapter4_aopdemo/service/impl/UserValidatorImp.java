package com.carterbrother.springbootpractice.chapter4_aopdemo.service.impl;

import com.carterbrother.springbootpractice.chapter4_aopdemo.pojo.User;
import com.carterbrother.springbootpractice.chapter4_aopdemo.service.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月19日 4:15 PM
 * @Copyright (c) carterbrother
 */
public class UserValidatorImp implements UserValidator {

    private static final Logger logger = LoggerFactory.getLogger(UserValidatorImp.class);

    /**
     * 校验User对象
     *
     * @param user
     * @return
     */
    @Override
    public boolean validate(User user) {

        logger.info("引入新接口：{} ", this.getClass().getSimpleName());
        return Objects.isNull(user);
    }
}
