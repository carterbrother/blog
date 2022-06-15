package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.DataRuleDict;
import com.springbootpractice.demo.easycode.plugin.service.DataRuleDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 数据权限规则字典(DataRuleDict)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("dataRuleDict")
public class DataRuleDictController {
    /**
     * 服务对象
     */
    @Resource
    private DataRuleDictService dataRuleDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public DataRuleDict selectOne(Long id) {
        return this.dataRuleDictService.queryById(id);
    }

}