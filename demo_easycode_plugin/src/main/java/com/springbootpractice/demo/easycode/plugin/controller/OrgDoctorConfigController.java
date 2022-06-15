package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.OrgDoctorConfig;
import com.springbootpractice.demo.easycode.plugin.service.OrgDoctorConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 机构跟医生的关系(OrgDoctorConfig)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("orgDoctorConfig")
public class OrgDoctorConfigController {
    /**
     * 服务对象
     */
    @Resource
    private OrgDoctorConfigService orgDoctorConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OrgDoctorConfig selectOne(Long id) {
        return this.orgDoctorConfigService.queryById(id);
    }

}