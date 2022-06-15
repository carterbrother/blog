package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.DoctorTopic;
import com.springbootpractice.demo.easycode.plugin.service.DoctorTopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 医生跟topic的绑定关系(DoctorTopic)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("doctorTopic")
public class DoctorTopicController {
    /**
     * 服务对象
     */
    @Resource
    private DoctorTopicService doctorTopicService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public DoctorTopic selectOne(Long id) {
        return this.doctorTopicService.queryById(id);
    }

}