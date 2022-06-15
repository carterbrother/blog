package com.springbootpractice.interceptor.web;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 说明：测试接口方法
 * @author carter
 * 创建时间： 2020年02月19日 11:00 下午
 **/
@RestController
public class IndexController {

    @GetMapping(path="hello")
    public Object hello(){
        return "hello";
    }

    @SneakyThrows
    @GetMapping(path="world")
    public Object world(){
        TimeUnit.SECONDS.sleep(1);
        return "world";
    }

}
