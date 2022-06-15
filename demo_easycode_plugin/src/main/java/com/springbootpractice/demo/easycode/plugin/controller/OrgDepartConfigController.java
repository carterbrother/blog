package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.OrgDepartConfig;
import com.springbootpractice.demo.easycode.plugin.service.OrgDepartConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 公司的部门配置(OrgDepartConfig)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("orgDepartConfig")
public class OrgDepartConfigController {
    /**
     * 服务对象
     */
    @Resource
    private OrgDepartConfigService orgDepartConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OrgDepartConfig selectOne(Long id) {
        return this.orgDepartConfigService.queryById(id);
    }

}