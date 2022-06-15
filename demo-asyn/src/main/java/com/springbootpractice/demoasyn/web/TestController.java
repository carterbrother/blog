package com.springbootpractice.demoasyn.web;
/*
 * 说明：控制器
 * @author  carter
 * 创建时间： 2019年07月15日 21:21
 */

import com.springbootpractice.demoasyn.service.AsynService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    private final AsynService asynService;

    @Autowired
    public TestController(AsynService asynService){
        this.asynService = asynService;
    }

    @GetMapping(path = "asyn")
    public Object asyn() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("asyn");
        stopWatch.start();
        asynService.asynHello();
        stopWatch.stop();
        return  stopWatch.prettyPrint();
    }


    @GetMapping(path = "normal")
    public Object hello2() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("normal");
        stopWatch.start();
        asynService.normalHello();
        stopWatch.stop();
        return  stopWatch.prettyPrint();
    }

}
