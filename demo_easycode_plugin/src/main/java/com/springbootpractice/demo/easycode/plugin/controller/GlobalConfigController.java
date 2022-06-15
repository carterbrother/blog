package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.GlobalConfig;
import com.springbootpractice.demo.easycode.plugin.service.GlobalConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 全局配置(GlobalConfig)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("globalConfig")
public class GlobalConfigController {
    /**
     * 服务对象
     */
    @Resource
    private GlobalConfigService globalConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GlobalConfig selectOne(Object id) {
        return this.globalConfigService.queryById(id);
    }

}