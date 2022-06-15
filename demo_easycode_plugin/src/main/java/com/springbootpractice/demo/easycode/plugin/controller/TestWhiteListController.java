package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.TestWhiteList;
import com.springbootpractice.demo.easycode.plugin.service.TestWhiteListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TestWhiteList)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("testWhiteList")
public class TestWhiteListController {
    /**
     * 服务对象
     */
    @Resource
    private TestWhiteListService testWhiteListService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TestWhiteList selectOne(Object id) {
        return this.testWhiteListService.queryById(id);
    }

}