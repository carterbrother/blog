package com.carterbrother.springbootpractice.chapter5_demo;

import com.carterbrother.springbootpractice.chapter5_demo.entity.User;
import com.carterbrother.springbootpractice.chapter5_demo.entity.enums.SexEnum;
import com.carterbrother.springbootpractice.chapter5_demo.service.JdbcTemplateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter5DemoApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(Chapter5DemoApplication.class);

    @Autowired
    private JdbcTemplateService jdbcTemplateService;



    @Test
    public void insert() {

        User user = new User();
        user.setUserName("carter");
        user.setSex(SexEnum.MALE);
        user.setNote("第一个用户");
        final int i = jdbcTemplateService.insertUser(user);
        Assert.assertTrue( i ==1);

        final List<User> users = jdbcTemplateService.findUsers(user.getUserName(), user.getNote());
        Assert.assertTrue(users.size()>0);
        Assert.assertTrue(users.get(0).getSex().equals(SexEnum.MALE));

        logger.info("insert : {}  , users: {} ",i,users);

    }

}
