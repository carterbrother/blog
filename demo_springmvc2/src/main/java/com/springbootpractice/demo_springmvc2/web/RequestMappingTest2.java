package com.springbootpractice.demo_springmvc2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 注解@RequestParam接受参数
 * @date 2019年05月16日 3:25 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class RequestMappingTest2 {

    @GetMapping(path = "/requestParam")
    @ResponseBody
    public Map<String,Object> requestParam(@RequestParam(name = "A",required = false) String a,
                                           @RequestParam(name = "B",required = false)Integer b ,
                                           @RequestParam(name = "C",required = false)Long c){

        Map<String,Object> dataMap = new HashMap<>(3);
        dataMap.put("a",a);
        dataMap.put("b",b);
        dataMap.put("c",c);

        return dataMap;

    }

}
