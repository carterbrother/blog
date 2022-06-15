package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.Memberstatus;
import com.springbootpractice.demo.easycode.plugin.service.MemberstatusService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Memberstatus)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("memberstatus")
public class MemberstatusController {
    /**
     * 服务对象
     */
    @Resource
    private MemberstatusService memberstatusService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Memberstatus selectOne(Integer id) {
        return this.memberstatusService.queryById(id);
    }

}