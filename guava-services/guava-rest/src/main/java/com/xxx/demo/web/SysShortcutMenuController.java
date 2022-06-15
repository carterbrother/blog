package com.xxx.demo.web;

import com.xxx.demo.configurer.core.Result;
import com.xxx.demo.dal.model.SysShortcutMenu;
import com.xxx.demo.biz.service.SysShortcutMenuService;
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
@Api("SysShortcutMenu模块")
@RestController
@RequestMapping("/sys/shortcut/menu")
public class SysShortcutMenuController {
    @Resource
    private SysShortcutMenuService sysShortcutMenuService;

    @ApiOperation("添加")
    @PostMapping
    public Result add(@RequestBody SysShortcutMenu sysShortcutMenu) {
        sysShortcutMenuService.save(sysShortcutMenu);
        return Result.genSuccessResult();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysShortcutMenuService.deleteById(id);
        return Result.genSuccessResult();
    }

    @ApiOperation("更新")
    @PutMapping
    public Result update(@RequestBody SysShortcutMenu sysShortcutMenu) {
        sysShortcutMenuService.update(sysShortcutMenu);
        return Result.genSuccessResult();
    }

    @ApiOperation("查单个")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysShortcutMenu sysShortcutMenu = sysShortcutMenuService.findById(id);
        return Result.genSuccessResult(sysShortcutMenu);
    }

    @ApiOperation("分页查询")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysShortcutMenu> list = sysShortcutMenuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.genSuccessResult(pageInfo);
    }
}
