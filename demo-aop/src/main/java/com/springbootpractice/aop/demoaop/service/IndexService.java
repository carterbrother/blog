package com.springbootpractice.aop.demoaop.service;

import com.springbootpractice.aop.demoaop.bean.IHelloWorld;
import org.springframework.stereotype.Service;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月02日 3:07 下午
 **/
@Service
public class IndexService {

    private IHelloWorld helloWorld;

    public IndexService(IHelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }


    public String sayHello(String name) {
        helloWorld.sayHello(name);
        return "ok";
    }
}
