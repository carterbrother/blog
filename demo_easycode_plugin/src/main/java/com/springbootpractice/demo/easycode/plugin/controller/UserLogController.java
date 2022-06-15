package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserLog;
import com.springbootpractice.demo.easycode.plugin.service.UserLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户操作日志表(UserLog)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userLog")
public class UserLogController {
    /**
     * 服务对象
     */
    @Resource
    private UserLogService userLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserLog selectOne(Long id) {
        return this.userLogService.queryById(id);
    }

}