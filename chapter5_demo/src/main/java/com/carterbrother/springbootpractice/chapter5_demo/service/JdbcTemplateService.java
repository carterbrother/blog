package com.carterbrother.springbootpractice.chapter5_demo.service;

import com.carterbrother.springbootpractice.chapter5_demo.entity.User;

import java.util.List;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月20日 5:58 PM
 * @Copyright (c) carterbrother
 */
public interface JdbcTemplateService {

    User getUser(Long id);

    List<User> findUsers(String userName, String note);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);


}
