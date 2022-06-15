package com.carterbrother.spriingbootpractice.chapter4_proxydemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 接口实现
 * @date 2019年02月15日 5:48 PM
 * @Copyright (c) carterbrother
 */
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public void sayHello(String name) {

        if (Objects.isNull(name) || "".equals(name.trim())){
            throw new RuntimeException("param is null");
        }
        logger.info("hello {} ",name);

    }
}
