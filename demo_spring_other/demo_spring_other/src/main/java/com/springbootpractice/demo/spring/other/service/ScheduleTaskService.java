package com.springbootpractice.demo.spring.other.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月13日 11:14 上午
 **/
@Service
@Slf4j
public class ScheduleTaskService {

    @Schedules({
            @Scheduled(initialDelay = 5 * 1000, fixedRate = 10 * 1000)
    })
    public void generateMonthReport() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行月报表计算");
    }

}
