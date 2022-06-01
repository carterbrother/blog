package com.spring.story.demo.openfeign.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carter.li
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name){

        log.info("request coming !！param：{}",name);

        return "hello ".concat(name).concat(" !");
    }

}
