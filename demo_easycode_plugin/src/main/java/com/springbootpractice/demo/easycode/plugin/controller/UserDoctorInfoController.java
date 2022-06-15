package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserDoctorInfo;
import com.springbootpractice.demo.easycode.plugin.service.UserDoctorInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 医生信息(UserDoctorInfo)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userDoctorInfo")
public class UserDoctorInfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserDoctorInfoService userDoctorInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserDoctorInfo selectOne(Long id) {
        return this.userDoctorInfoService.queryById(id);
    }

}