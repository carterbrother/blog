package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserLogin;
import com.springbootpractice.demo.easycode.plugin.service.UserLoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录表(UserLogin)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userLogin")
public class UserLoginController {
    /**
     * 服务对象
     */
    @Resource
    private UserLoginService userLoginService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserLogin selectOne(Long id) {
        return this.userLoginService.queryById(id);
    }

}