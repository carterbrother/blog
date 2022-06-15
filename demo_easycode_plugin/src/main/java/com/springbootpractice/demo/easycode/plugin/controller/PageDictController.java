package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.PageDict;
import com.springbootpractice.demo.easycode.plugin.service.PageDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 页面字典(对应后台系统的导航条)(PageDict)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("pageDict")
public class PageDictController {
    /**
     * 服务对象
     */
    @Resource
    private PageDictService pageDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public PageDict selectOne(Long id) {
        return this.pageDictService.queryById(id);
    }

}