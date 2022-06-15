package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.InterfaceConfig;
import com.springbootpractice.demo.easycode.plugin.service.InterfaceConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 接口配置(InterfaceConfig)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("interfaceConfig")
public class InterfaceConfigController {
    /**
     * 服务对象
     */
    @Resource
    private InterfaceConfigService interfaceConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public InterfaceConfig selectOne(Long id) {
        return this.interfaceConfigService.queryById(id);
    }

}