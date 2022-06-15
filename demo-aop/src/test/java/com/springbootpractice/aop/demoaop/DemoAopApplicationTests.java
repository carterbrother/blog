package com.springbootpractice.aop.demoaop;

import com.springbootpractice.aop.demoaop.aop.MyInterceptor;
import com.springbootpractice.aop.demoaop.aop.ProxyBean;
import com.springbootpractice.aop.demoaop.bean.HelloWorldService;
import com.springbootpractice.aop.demoaop.bean.IHelloWorld;
import org.junit.jupiter.api.Test;

class DemoAopApplicationTests {

    @Test
    void proxyTest() {

        IHelloWorld helloWorld = new HelloWorldService();

        IHelloWorld proxy = (IHelloWorld) ProxyBean.getProxyBean(helloWorld,new MyInterceptor());

        proxy.sayHello("lifesense");


        System.out.println("======");

        proxy.sayHello("");

    }

}
