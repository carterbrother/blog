package com.springbootpractice.demo_springmvc2.web;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description url参数
 * @date 2019年05月16日 3:25 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class RequestMappingTest5 {

    @RequestMapping(path = "/pathParam/{a}")
    @ResponseBody
    public Map<String,Object> pathParam(@PathVariable("a") String a){

        Map<String,Object> dataMap = new HashMap<>(3);
        dataMap.put("a",a);
        return dataMap;

    }

}
