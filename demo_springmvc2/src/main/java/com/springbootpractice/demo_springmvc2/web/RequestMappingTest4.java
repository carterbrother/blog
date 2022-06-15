package com.springbootpractice.demo_springmvc2.web;

import com.springbootpractice.demo_springmvc2.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description JSON参数
 * @date 2019年05月16日 3:25 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class RequestMappingTest4 {

    @RequestMapping(path = "/json")
    @ResponseBody
    public Map<String,Object> json(@RequestBody User user){

        Map<String,Object> dataMap = new HashMap<>(3);

        final User build = User.builder().id(user.getId()).name("build:".concat(user.getName())).sex(user.getSex()).build();
        dataMap.put("user",user);
        dataMap.put("build",build);

        return dataMap;

    }

}
