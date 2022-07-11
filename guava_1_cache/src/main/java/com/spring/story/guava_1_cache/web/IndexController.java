package com.spring.story.guava_1_cache.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    
    @GetMapping(path="/hello")
    public String hello() {
        return "hello world!" ;
    }
}
