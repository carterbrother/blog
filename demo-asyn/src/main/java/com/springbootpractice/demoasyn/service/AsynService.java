package com.springbootpractice.demoasyn.service;/*
 * 说明：服务类对比
 * @author  carter
 * 创建时间： 2019年07月15日 21:19
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AsynService {
    public static final Logger logger = LoggerFactory.getLogger(AsynService.class);

    @Async
    public void asynHello() throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);

        logger.info("hello world");

    }


    public void normalHello() throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);

        logger.info("hello world");

    }

}
