package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.UserThirdpartyAccount;
import com.springbootpractice.demo.easycode.plugin.service.UserThirdpartyAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 第三方账号(UserThirdpartyAccount)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("userThirdpartyAccount")
public class UserThirdpartyAccountController {
    /**
     * 服务对象
     */
    @Resource
    private UserThirdpartyAccountService userThirdpartyAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserThirdpartyAccount selectOne(Integer id) {
        return this.userThirdpartyAccountService.queryById(id);
    }

}