package com.springbootpractice.demo.easycode.plugin.controller;

import com.springbootpractice.demo.easycode.plugin.entity.Carrier;
import com.springbootpractice.demo.easycode.plugin.service.CarrierService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 运营商(Carrier)表控制层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@RestController
@RequestMapping("carrier")
public class CarrierController {
    /**
     * 服务对象
     */
    @Resource
    private CarrierService carrierService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Carrier selectOne(String id) {
        return this.carrierService.queryById(id);
    }

}