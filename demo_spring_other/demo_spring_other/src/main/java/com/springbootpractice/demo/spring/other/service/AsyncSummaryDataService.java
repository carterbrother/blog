package com.springbootpractice.demo.spring.other.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * 说明：统计数据计算业务代码
 * @author carter
 * 创建时间： 2020年01月13日 10:27 上午
 **/
@Service
@Slf4j
public class AsyncSummaryDataService {

    @Async
    public void calculateDayNewData(LocalDate day) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("calculateDayNewData 执行线程：{}", Thread.currentThread().getName());
    }


    @Async
    public void calculateDayLeftData(LocalDate day) {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("calculateDayLeftData 执行线程：{}", Thread.currentThread().getName());

    }

    @Async
    public void calculateDayActiveData(LocalDate day) {
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("calculateDayActiveData 执行线程：{}", Thread.currentThread().getName());

    }


}
