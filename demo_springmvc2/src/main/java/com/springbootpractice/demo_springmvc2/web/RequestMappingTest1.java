package com.springbootpractice.demo_springmvc2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 数组参数
 * @date 2019年05月16日 3:25 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class RequestMappingTest1 {

    @GetMapping(path = "/array")
    @ResponseBody
    public Map<String,Object> array(String[] a, Integer[] b , Long[] c){

        Map<String,Object> dataMap = new HashMap<>(3);
        dataMap.put("a",a);
        dataMap.put("b",b);
        dataMap.put("c",c);

        return dataMap;

    }

}
