package com.springbootpractice.demo_springmvc2.web;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 格式化参数
 * @date 2019年05月16日 3:25 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class RequestMappingTest6 {

    @RequestMapping(path = "/date")
    @ResponseBody
    public Map<String,Object> date(Date a){

        Map<String,Object> dataMap = new HashMap<>(3);
        dataMap.put("a",a);
        return dataMap;

    }


    @RequestMapping(path = "/localDateTime")
    @ResponseBody
    public Map<String,Object> localDateTime(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime a){

        Map<String,Object> dataMap = new HashMap<>(3);
        dataMap.put("a",a);
        return dataMap;

    }


    @RequestMapping(path = "/localDate")
    @ResponseBody
    public Map<String,Object> localDate(LocalDate a){

        Map<String,Object> dataMap = new HashMap<>(3);
        dataMap.put("a",a);
        return dataMap;

    }

    @RequestMapping(path = "/number")
    @ResponseBody
    public Map<String,Object> number(@NumberFormat(pattern = "#,###.##") Double a){

        Map<String,Object> dataMap = new HashMap<>(3);
        dataMap.put("a",a);
        return dataMap;

    }

}
