package com.carterbrother.springbootpractice.chapter1_0xmlmvcdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 实例控制器
 * @date 2019年02月13日 3:56 PM
 * @Copyright (c) carterbrother
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello(){
        Map<String,String> map = new HashMap<>(1);

        map.put("key","value");

        return map;
    }
}
