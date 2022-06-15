package com.xxx.demo.web;

import com.xxx.demo.configurer.core.Result;
import com.xxx.demo.dal.model.SysMenu;
import com.xxx.demo.biz.service.SysMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/19.
*/
@Api("SysMenu模块")
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @ApiOperation("添加")
    @PostMapping
    public Result add(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.genSuccessResult();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysMenuService.deleteById(id);
        return Result.genSuccessResult();
    }

    @ApiOperation("更新")
    @PutMapping
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return Result.genSuccessResult();
    }

    @ApiOperation("查单个")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysMenu sysMenu = sysMenuService.findById(id);
        return Result.genSuccessResult(sysMenu);
    }

    @ApiOperation("分页查询")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysMenu> list = sysMenuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.genSuccessResult(pageInfo);
    }
}
