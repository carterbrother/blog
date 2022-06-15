package com.springbootpractice.demowebcontainer.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者:     carter
 * 创建日期:  2019/6/19 上午7:57
 * 描述:     TODO
 */
@RestController
public class IndexController {

    @GetMapping(path = "/index")
    public Object index(){
        return "hello world";
    }

}
