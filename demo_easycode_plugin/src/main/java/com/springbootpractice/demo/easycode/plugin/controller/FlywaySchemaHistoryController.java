package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.FlywaySchemaHistory;
import com.springbootpractice.demo.easycode.plugin.service.FlywaySchemaHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FlywaySchemaHistory)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("flywaySchemaHistory")
public class FlywaySchemaHistoryController {
    /**
     * 服务对象
     */
    @Resource
    private FlywaySchemaHistoryService flywaySchemaHistoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FlywaySchemaHistory selectOne(Integer id) {
        return this.flywaySchemaHistoryService.queryById(id);
    }

}