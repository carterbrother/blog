package com.carterbrother.springbootpractice.springsecuritydemo.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 用户所具备的接口
 * @date 2019年04月10日 3:10 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class UserController {


    @RequestMapping(value = "/user/welcome",method = RequestMethod.GET)
    public String welcome(){
        return  "hello -> " + SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(value = "/user/details")
    public Object details(){
        return  SecurityContextHolder.getContext().getAuthentication();
    }



}
