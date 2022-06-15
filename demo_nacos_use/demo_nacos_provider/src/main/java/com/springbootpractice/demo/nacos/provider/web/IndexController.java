package com.springbootpractice.demo.nacos.provider.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carter
 * create_date  2020/5/27 11:16
 * description     测试控制器
 */
@RestController
@Slf4j
public class IndexController {

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        log.info("name is : {}",name );
        return "hello "+name;
    }

}
