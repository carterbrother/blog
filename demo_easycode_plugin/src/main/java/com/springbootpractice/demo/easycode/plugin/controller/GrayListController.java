package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.GrayList;
import com.springbootpractice.demo.easycode.plugin.service.GrayListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 灰度名单(GrayList)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("grayList")
public class GrayListController {
    /**
     * 服务对象
     */
    @Resource
    private GrayListService grayListService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GrayList selectOne(Object id) {
        return this.grayListService.queryById(id);
    }

}