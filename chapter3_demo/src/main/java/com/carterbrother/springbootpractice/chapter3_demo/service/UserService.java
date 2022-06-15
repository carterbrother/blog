package com.carterbrother.springbootpractice.chapter3_demo.service;

import com.carterbrother.springbootpractice.chapter3_demo.pojo.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 10:40 AM
 * @Copyright (c) carterbrother
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void printUser2(User2 user2){
        logger.info("id:{} , userName:{} , note: {}",user2.getId(), user2.getUserName(), user2.getNote());
    }

}
