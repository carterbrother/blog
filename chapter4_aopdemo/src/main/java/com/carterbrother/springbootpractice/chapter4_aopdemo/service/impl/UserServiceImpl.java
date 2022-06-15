package com.carterbrother.springbootpractice.chapter4_aopdemo.service.impl;

import com.carterbrother.springbootpractice.chapter4_aopdemo.pojo.User;
import com.carterbrother.springbootpractice.chapter4_aopdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月19日 3:28 PM
 * @Copyright (c) carterbrother
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 打印出User信息
     *
     * @param user
     */
    @Override
    public void printUser(User user) {

        if (Objects.isNull(user)){
            throw  new NullPointerException("请检查参数是否为空");
        }
        logger.info("user.id:{} , user.userName:{} , user.birthday: {}", user.getId(),user.getUserName(),user.getBirthday());

    }
}
