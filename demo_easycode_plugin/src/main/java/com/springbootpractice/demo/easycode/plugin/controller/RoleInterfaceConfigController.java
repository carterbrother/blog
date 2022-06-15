package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.RoleInterfaceConfig;
import com.springbootpractice.demo.easycode.plugin.service.RoleInterfaceConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色权限项对应关系(RoleInterfaceConfig)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("roleInterfaceConfig")
public class RoleInterfaceConfigController {
    /**
     * 服务对象
     */
    @Resource
    private RoleInterfaceConfigService roleInterfaceConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RoleInterfaceConfig selectOne(Long id) {
        return this.roleInterfaceConfigService.queryById(id);
    }

}