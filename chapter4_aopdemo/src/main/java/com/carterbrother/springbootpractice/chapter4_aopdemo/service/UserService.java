package com.carterbrother.springbootpractice.chapter4_aopdemo.service;

import com.carterbrother.springbootpractice.chapter4_aopdemo.pojo.User;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月19日 3:26 PM
 * @Copyright (c) carterbrother
 */
public interface UserService {

    /**
     * 打印出User信息
     * @param user
     */
    void printUser(User user);

}
