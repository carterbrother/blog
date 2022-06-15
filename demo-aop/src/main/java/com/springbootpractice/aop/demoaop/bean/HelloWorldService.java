package com.springbootpractice.aop.demoaop.bean;

import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月02日 9:42 上午
 **/
@Service
public class HelloWorldService implements IHelloWorld {
    /**
     * 问候
     * @param name 姓名
     */
    @Override
    public void sayHello(String name) {
        if (Objects.isNull(name) || Objects.equals("", name)) {
            throw new RuntimeException("name为空");
        }
        System.out.println("hello  " + name);
    }
}
