package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.RoleDataRuleConfig;
import com.springbootpractice.demo.easycode.plugin.service.RoleDataRuleConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色权限项对应关系(RoleDataRuleConfig)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("roleDataRuleConfig")
public class RoleDataRuleConfigController {
    /**
     * 服务对象
     */
    @Resource
    private RoleDataRuleConfigService roleDataRuleConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RoleDataRuleConfig selectOne(Long id) {
        return this.roleDataRuleConfigService.queryById(id);
    }

}