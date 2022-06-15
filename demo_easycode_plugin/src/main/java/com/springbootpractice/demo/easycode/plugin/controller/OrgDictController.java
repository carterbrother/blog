package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.OrgDict;
import com.springbootpractice.demo.easycode.plugin.service.OrgDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 机构或者医院(OrgDict)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("orgDict")
public class OrgDictController {
    /**
     * 服务对象
     */
    @Resource
    private OrgDictService orgDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OrgDict selectOne(Long id) {
        return this.orgDictService.queryById(id);
    }

}