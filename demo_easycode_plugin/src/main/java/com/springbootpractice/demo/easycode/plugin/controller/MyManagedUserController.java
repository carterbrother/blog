package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.MyManagedUser;
import com.springbootpractice.demo.easycode.plugin.service.MyManagedUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 我管理的用户(MyManagedUser)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("myManagedUser")
public class MyManagedUserController {
    /**
     * 服务对象
     */
    @Resource
    private MyManagedUserService myManagedUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MyManagedUser selectOne(Integer id) {
        return this.myManagedUserService.queryById(id);
    }

}