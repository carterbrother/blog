package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserCardBindHis;
import com.springbootpractice.demo.easycode.plugin.service.UserCardBindHisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserCardBindHis)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userCardBindHis")
public class UserCardBindHisController {
    /**
     * 服务对象
     */
    @Resource
    private UserCardBindHisService userCardBindHisService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserCardBindHis selectOne(Integer id) {
        return this.userCardBindHisService.queryById(id);
    }

}