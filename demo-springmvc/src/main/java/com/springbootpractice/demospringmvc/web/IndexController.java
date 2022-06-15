package com.springbootpractice.demospringmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月03日 5:35 下午
 **/
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
