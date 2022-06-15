package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserAttribute;
import com.springbootpractice.demo.easycode.plugin.service.UserAttributeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户属性表(UserAttribute)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userAttribute")
public class UserAttributeController {
    /**
     * 服务对象
     */
    @Resource
    private UserAttributeService userAttributeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserAttribute selectOne(Integer id) {
        return this.userAttributeService.queryById(id);
    }

}