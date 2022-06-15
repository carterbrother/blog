package com.springbootpractice.lesson.lesson3.web;

import com.springbootpractice.lesson.lesson3.domain.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description xml输入，xml输出
 * @date 2019年05月15日 10:40 AM
 * @Copyright (c) carterbrother
 */
@Controller
public class UserController {

    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User create(@RequestBody User user)
    {
        user.setName("lesson3 Name: ".concat(user.getName()));
        user.setAge(user.getAge()+18);
        return user;
    }

}
