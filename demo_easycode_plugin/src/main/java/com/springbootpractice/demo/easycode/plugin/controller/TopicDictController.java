package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.TopicDict;
import com.springbootpractice.demo.easycode.plugin.service.TopicDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 研究课题字典表(TopicDict)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@RestController
@RequestMapping("topicDict")
public class TopicDictController {
    /**
     * 服务对象
     */
    @Resource
    private TopicDictService topicDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TopicDict selectOne(Long id) {
        return this.topicDictService.queryById(id);
    }

}