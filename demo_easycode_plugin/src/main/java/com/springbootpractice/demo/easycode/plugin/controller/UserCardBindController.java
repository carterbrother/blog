package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserCardBind;
import com.springbootpractice.demo.easycode.plugin.service.UserCardBindService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserCardBind)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userCardBind")
public class UserCardBindController {
    /**
     * 服务对象
     */
    @Resource
    private UserCardBindService userCardBindService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserCardBind selectOne(Long id) {
        return this.userCardBindService.queryById(id);
    }

}