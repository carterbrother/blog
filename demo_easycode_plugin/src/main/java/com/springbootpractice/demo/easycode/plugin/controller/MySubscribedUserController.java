package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.MySubscribedUser;
import com.springbootpractice.demo.easycode.plugin.service.MySubscribedUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 我关注的用户(MySubscribedUser)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("mySubscribedUser")
public class MySubscribedUserController {
    /**
     * 服务对象
     */
    @Resource
    private MySubscribedUserService mySubscribedUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MySubscribedUser selectOne(Integer id) {
        return this.mySubscribedUserService.queryById(id);
    }

}