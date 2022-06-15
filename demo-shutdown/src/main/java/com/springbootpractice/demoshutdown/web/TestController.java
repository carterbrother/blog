package com.springbootpractice.demoshutdown.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * 说明：测试控制器，模拟正常的业务请求
 * @author  carter
 * 创建时间： 2019年07月23日 10:17
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping(path = "/hello")
    public Object hello(){

        log.info("开始处理 hello 请求");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("处理完毕 ");
        return "hello " + System.currentTimeMillis() ;
    }

}
