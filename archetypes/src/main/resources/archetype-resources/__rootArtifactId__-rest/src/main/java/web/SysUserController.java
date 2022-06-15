package ${package}.web;

import ${package}.configurer.core.Result;
import ${package}.dal.model.SysUser;
import ${package}.biz.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ${projectAuthor} on 2020/03/19.
 */
@Api("SysUser模块")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @ApiOperation("添加")
    @PostMapping
    public Result add(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return Result.genSuccessResult();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sysUserService.deleteById(id);
        return Result.genSuccessResult();
    }

    @ApiOperation("更新")
    @PutMapping
    public Result update(@RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);
        return Result.genSuccessResult();
    }

    @ApiOperation("查单个")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SysUser sysUser = sysUserService.findById(id);
        return Result.genSuccessResult(sysUser);
    }

    @ApiOperation("分页查询")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = sysUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.genSuccessResult(pageInfo);
    }
}
