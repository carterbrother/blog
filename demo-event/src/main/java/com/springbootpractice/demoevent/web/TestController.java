package com.springbootpractice.demoevent.web;

import com.springbootpractice.demoevent.event.EatEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明 测试控制器
 *
 * @author carter
 * 创建时间 2019年07月12日 13:23
 **/
@RestController
public class TestController {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public TestController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping(path = "/eatOver")
    public Object eatOver() {
        EatEvent xEvent = new EatEvent(true);
        applicationEventPublisher.publishEvent(xEvent);
        return "eat over and publish event success";
    }

}
