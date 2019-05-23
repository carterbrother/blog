package com.springx.demoservice.web;

import com.springx.bootdubbo.common.util.UUIDUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 用户控制器
 * @date 2019年05月22日 4:24 PM
 * @Copyright (c) carterbrother
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @GetMapping(path = "/hello")
    public Object hello(){
        Map<String,Object> data = new HashMap<>(1);
        data.put("hello", UUIDUtil.uuid());
        return data;
    }

}
