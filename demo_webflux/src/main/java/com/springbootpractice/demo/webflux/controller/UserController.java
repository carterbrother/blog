package com.springbootpractice.demo.webflux.controller;

import com.springbootpractice.demo.webflux.core.UserValidator;
import com.springbootpractice.demo.webflux.dao.entity.User;
import com.springbootpractice.demo.webflux.service.UserService;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月15日 6:43 下午
 **/
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/user/{id}")
    public Mono<User> getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @GetMapping(path = "/user/{userName}/{note}")
    public Flux<User> getUser(@PathVariable("userName") String userName, @PathVariable("note") String note){
        return userService.findByUserNameAndNote(userName,note);
    }

    @GetMapping(path = "/user/insert/{user}")
    public Mono<User> insertUser(@Valid  @PathVariable("user")  User user){
        return userService.insertUser(user);
    }

//    @InitBinder
//    public void initBinder(DataBinder binder){
//        binder.setValidator(new UserValidator());
//    }
}
