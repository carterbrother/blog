package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.RolePageConfig;
import com.springbootpractice.demo.easycode.plugin.service.RolePageConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色页面关联表(RolePageConfig)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("rolePageConfig")
public class RolePageConfigController {
    /**
     * 服务对象
     */
    @Resource
    private RolePageConfigService rolePageConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RolePageConfig selectOne(Long id) {
        return this.rolePageConfigService.queryById(id);
    }

}