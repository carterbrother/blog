package com.carterbrother.springbootpractice.chapter3_demo.config;

import com.carterbrother.springbootpractice.chapter3_demo.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description ioc的配置类
 * @date 2019年02月14日 6:10 PM
 * @Copyright (c) carterbrother
 */
@Configuration
public class AppConfig {

    @Bean(name = "userxxx")
    public User user(){
        User user = new User();
        user.setId(10086L);
        user.setUserName("userName_".concat(String.valueOf(user.getId())));
        user.setNote("note_".concat(user.getUserName()));
        return user;
    }
}
