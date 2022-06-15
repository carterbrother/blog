package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.RoleUserConfig;
import com.springbootpractice.demo.easycode.plugin.service.RoleUserConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色用户对应关系(RoleUserConfig)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("roleUserConfig")
public class RoleUserConfigController {
    /**
     * 服务对象
     */
    @Resource
    private RoleUserConfigService roleUserConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RoleUserConfig selectOne(Long id) {
        return this.roleUserConfigService.queryById(id);
    }

}