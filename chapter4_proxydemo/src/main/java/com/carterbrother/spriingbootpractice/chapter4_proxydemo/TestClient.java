package com.carterbrother.spriingbootpractice.chapter4_proxydemo;

import com.carterbrother.spriingbootpractice.chapter4_proxydemo.intercept.MyInterceptor;
import com.carterbrother.spriingbootpractice.chapter4_proxydemo.proxy.ProxyBean;
import com.carterbrother.spriingbootpractice.chapter4_proxydemo.service.HelloService;
import com.carterbrother.spriingbootpractice.chapter4_proxydemo.service.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月19日 2:43 PM
 * @Copyright (c) carterbrother
 */
public class TestClient {

    private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

    public static void main(String[] args) {

        HelloService helloServiceProxy = (HelloService) ProxyBean.getProxyBean(new HelloServiceImpl(),new MyInterceptor());

        helloServiceProxy.sayHello("xxx");

        logger.info("===> name is null test ......");

        helloServiceProxy.sayHello(null);
    }

}
