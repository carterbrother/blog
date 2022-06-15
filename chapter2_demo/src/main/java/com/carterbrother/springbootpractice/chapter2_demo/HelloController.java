package com.carterbrother.springbootpractice.chapter2_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 控制器
 * @date 2019年02月13日 6:03 PM
 * @Copyright (c) carterbrother
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


}
