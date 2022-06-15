package com.xxx.demo.web;

import com.xxx.demo.configurer.core.Result;
import com.xxx.demo.dal.model.SysUserMenu;
import com.xxx.demo.biz.service.SysUserMenuService;
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
@Api("SysUserMenu模块")
@RestController
@RequestMapping("/sys/user/menu")
public class SysUserMenuController {
    @Resource
    private SysUserMenuService sysUserMenuService;

    @ApiOperation("添加")
    @PostMapping
    public Result add(@RequestBody SysUserMenu sysUserMenu) {
        sysUserMenuService.save(sysUserMenu);
        return Result.genSuccessResult();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysUserMenuService.deleteById(id);
        return Result.genSuccessResult();
    }

    @ApiOperation("更新")
    @PutMapping
    public Result update(@RequestBody SysUserMenu sysUserMenu) {
        sysUserMenuService.update(sysUserMenu);
        return Result.genSuccessResult();
    }

    @ApiOperation("查单个")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysUserMenu sysUserMenu = sysUserMenuService.findById(id);
        return Result.genSuccessResult(sysUserMenu);
    }

    @ApiOperation("分页查询")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUserMenu> list = sysUserMenuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.genSuccessResult(pageInfo);
    }
}
