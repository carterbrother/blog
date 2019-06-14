package com.example.demojob.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description  test controller , test job
 * @date 2019年06月14日 13:25
 * @Copyright (c) carterbrother
 */
@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    @ResponseBody
    public Object hello(){
        return "hello world";
    }

}
