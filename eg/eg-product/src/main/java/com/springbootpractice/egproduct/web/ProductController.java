package com.springbootpractice.egproduct.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springbootpractice.api.user.UserApi;
import com.springbootpractice.api.user.dto.UserPro;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 测试使用feign调用其它服务
 * @date 2019年06月20日 17:00
 * @Copyright (c) carterbrother
 */
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private UserApi userApi;

    @GetMapping("/addUser/{id}/{userName}")
    public Map<String,Object> addUser(@PathVariable("id") Long id, @PathVariable("userName") String userName)
    {
        log.info("addUser invoke ");

        UserPro userPro = new UserPro();
        userPro.setId(id);
        userPro.setUserName(userName);

        return userApi.insertUser(userPro);
    }


    @GetMapping("/updateUser/{id}/{userName}")
    public Map<String,Object> updateUser(@PathVariable("id") Long id, @PathVariable("userName") String userName)
    {
        log.info("updateUser invoke ");
        return userApi.updateUserName(userName,id);
    }


    @GetMapping("/getUser/{id}")
    public UserPro getUser(@PathVariable("id") Long id)
    {
        log.info("getUser invoke ");
        return userApi.getUserPro(id);
    }


    @GetMapping(path = "/timeout")
    @HystrixCommand(fallbackMethod = "error")
    public String timeOut(){

        try {
            TimeUnit.SECONDS.sleep(RandomUtils.nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "success  熔断测试";

    }

    public String error(){
        return " error 超时出错";
    }


}
