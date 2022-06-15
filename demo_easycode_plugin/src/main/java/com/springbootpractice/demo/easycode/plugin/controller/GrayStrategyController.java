package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.GrayStrategy;
import com.springbootpractice.demo.easycode.plugin.service.GrayStrategyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 灰度策略(GrayStrategy)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("grayStrategy")
public class GrayStrategyController {
    /**
     * 服务对象
     */
    @Resource
    private GrayStrategyService grayStrategyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GrayStrategy selectOne(Object id) {
        return this.grayStrategyService.queryById(id);
    }

}