package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserPatientInfo;
import com.springbootpractice.demo.easycode.plugin.service.UserPatientInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 患者信息(UserPatientInfo)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userPatientInfo")
public class UserPatientInfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserPatientInfoService userPatientInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserPatientInfo selectOne(Long id) {
        return this.userPatientInfoService.queryById(id);
    }

}