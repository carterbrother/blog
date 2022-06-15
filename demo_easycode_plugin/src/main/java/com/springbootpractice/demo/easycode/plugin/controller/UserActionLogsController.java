package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserActionLogs;
import com.springbootpractice.demo.easycode.plugin.service.UserActionLogsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserActionLogs)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userActionLogs")
public class UserActionLogsController {
    /**
     * 服务对象
     */
    @Resource
    private UserActionLogsService userActionLogsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserActionLogs selectOne(Integer id) {
        return this.userActionLogsService.queryById(id);
    }

}