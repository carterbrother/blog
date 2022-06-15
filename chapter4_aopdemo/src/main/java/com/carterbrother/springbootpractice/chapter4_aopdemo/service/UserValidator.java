package com.carterbrother.springbootpractice.chapter4_aopdemo.service;

import com.carterbrother.springbootpractice.chapter4_aopdemo.pojo.User;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月19日 4:14 PM
 * @Copyright (c) carterbrother
 */
public interface UserValidator {
    /**
     * 校验User对象
     * @param user
     * @return
     */
    boolean validate(User user);

}
