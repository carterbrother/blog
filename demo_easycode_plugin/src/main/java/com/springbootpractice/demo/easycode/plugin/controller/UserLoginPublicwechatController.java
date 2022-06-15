package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserLoginPublicwechat;
import com.springbootpractice.demo.easycode.plugin.service.UserLoginPublicwechatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 小程序进入的公众号记录(UserLoginPublicwechat)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userLoginPublicwechat")
public class UserLoginPublicwechatController {
    /**
     * 服务对象
     */
    @Resource
    private UserLoginPublicwechatService userLoginPublicwechatService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserLoginPublicwechat selectOne(Long id) {
        return this.userLoginPublicwechatService.queryById(id);
    }

}