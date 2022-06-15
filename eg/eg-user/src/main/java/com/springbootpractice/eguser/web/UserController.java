package com.springbootpractice.eguser.web;

import com.springbootpractice.api.user.UserApi;
import com.springbootpractice.api.user.dto.UserPro;
import com.springbootpractice.eguser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 用户服务对外的REST服务
 * @date 2019年06月20日 16:42
 * @Copyright (c) carterbrother
 */
@Slf4j
@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public UserPro getUserPro(Long id) {
        log.info(" user  getUserPro invoke ");

        return userService.getUserPro(id);
    }

    @Override
    public Map<String, Object> updateUserName(String userName, Long id) {
        log.info(" user  updateUserName invoke ");

        return userService.updateUserName(userName, id);
    }


    @Override
    public Map<String, Object> insertUser(UserPro userPro) {

        log.info(" user  insertUser invoke ");

        return userService.insertUser(userPro);
    }
}
