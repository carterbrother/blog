package com.carterbrother.springbootpractice.chapter4_aopdemo.web;

import com.carterbrother.springbootpractice.chapter4_aopdemo.pojo.User;
import com.carterbrother.springbootpractice.chapter4_aopdemo.service.UserService;
import com.carterbrother.springbootpractice.chapter4_aopdemo.service.UserValidator;
import com.carterbrother.springbootpractice.chapter4_aopdemo.service.impl.UserServiceImpl2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月19日 3:51 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl2 userService2;

    @GetMapping("/test")
    public void test(){
        User user = new User();
        user.setId(10086L);
        user.setUserName("carter");
        user.setBirthday(LocalDate.now());

        userService.printUser(user);
    }

    @GetMapping("/testV")
    public void testV(){
        User user = new User();
        user.setId(10086L);
        user.setUserName("carter");
        user.setBirthday(LocalDate.now());

        UserValidator userValidator = (UserValidator) userService;
        boolean validateSucc =  userValidator.validate(user);
        if (validateSucc){
            userService.printUser(user);
        }
    }
    @GetMapping("/testC")
    public void testC() {
        User user = new User();
        user.setId(10086L);
        user.setUserName("carter2");
        user.setBirthday(LocalDate.now());
        userService2.printUser(user);
        logger.info("userService: {} , userService2: {} ",userService,userService2);
    }


}
