package com.springbootpractice.aop.demoaop.web;

import com.springbootpractice.aop.demoaop.service.IndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月02日 3:06 下午
 **/
@RestController
public class IndexController {


    private final IndexService indexService;

    public IndexController(IndexService indexService){
        this.indexService = indexService;
    }

    @GetMapping(path = "/sayHello")
    public Object sayHello(@RequestParam("name") String name){
       return indexService.sayHello(name);
    }

}
