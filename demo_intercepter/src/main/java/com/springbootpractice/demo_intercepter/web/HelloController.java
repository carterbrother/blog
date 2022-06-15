package com.springbootpractice.demo_intercepter.web;

import com.springbootpractice.demo_intercepter.core.BaseException;
import com.springbootpractice.demo_intercepter.core.RestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 测试控制器
 * @date 2019年05月16日 6:24 PM
 * @Copyright (c) carterbrother
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping(path = "/hello")
    @ResponseBody
    public Object hello(){

        log.info("invoke hello method ");

        RestContext restContext = RestContext.getInstance();

        return restContext;
    }


    @GetMapping(path = "/hello2")
    public void hello2(){

        log.info("invoke hello2 method ");

        RestContext restContext = RestContext.getInstance();

        return ;
    }


    @GetMapping(path = "/helloEx")
    public void helloEx(){

        log.info("invoke helloEx method ");

        throw new BaseException(504,"xxxx");


    }



    @GetMapping(path = "/helloEx2")
    public void helloEx2(){

        log.info("invoke helloEx method ");

        throw new IllegalArgumentException("test xxxx");


    }


}
