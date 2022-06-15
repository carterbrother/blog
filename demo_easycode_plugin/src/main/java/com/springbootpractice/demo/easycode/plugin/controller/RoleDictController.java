package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.RoleDict;
import com.springbootpractice.demo.easycode.plugin.service.RoleDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统角色(RoleDict)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("roleDict")
public class RoleDictController {
    /**
     * 服务对象
     */
    @Resource
    private RoleDictService roleDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RoleDict selectOne(Long id) {
        return this.roleDictService.queryById(id);
    }

}