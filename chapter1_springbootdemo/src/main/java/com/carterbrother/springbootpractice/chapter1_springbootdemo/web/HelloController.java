package com.carterbrother.springbootpractice.chapter1_springbootdemo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 控制器实例
 * @date 2019年02月13日 4:57 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public Map<String, String> hello(){
        Map<String,String> map = new HashMap<>(1);
        map.put("key","value");
        return map;
    }

}
