package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.SystemDict;
import com.springbootpractice.demo.easycode.plugin.service.SystemDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 接入的系统（按照实际的产品来分）(SystemDict)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("systemDict")
public class SystemDictController {
    /**
     * 服务对象
     */
    @Resource
    private SystemDictService systemDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SystemDict selectOne(Long id) {
        return this.systemDictService.queryById(id);
    }

}