package com.springbootpractice.lesson.lesson_1.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 第一个简单的springboot接口
 * @date 2019年05月14日 8:54 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class LessonController {

    @GetMapping("/hello")
    public Object hello(){
        return "hello";
    }
}
