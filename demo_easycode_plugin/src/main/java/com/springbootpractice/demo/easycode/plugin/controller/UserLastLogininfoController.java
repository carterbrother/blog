package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserLastLogininfo;
import com.springbootpractice.demo.easycode.plugin.service.UserLastLogininfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户最后登录信息(UserLastLogininfo)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userLastLogininfo")
public class UserLastLogininfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserLastLogininfoService userLastLogininfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserLastLogininfo selectOne(Long id) {
        return this.userLastLogininfoService.queryById(id);
    }

}