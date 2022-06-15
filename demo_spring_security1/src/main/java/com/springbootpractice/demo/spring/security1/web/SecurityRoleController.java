package com.springbootpractice.demo.spring.security1.web;

import com.springbootpractice.demo.spring.security1.dao.entity.SecurityLoginEntity;
import com.springbootpractice.demo.spring.security1.param.core.PageRestRes;
import com.springbootpractice.demo.spring.security1.param.core.UpdateRestRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 说明：前端页面
 * @author carter
 * 创建时间： 2020年02月08日 4:22 下午
 **/
@RestController
@RequestMapping(path = "admin/role")
public class SecurityRoleController {
    /**
     * 查询页面
     * @return
     */
    @GetMapping(path = "page/list")
    public ModelAndView pageList() {
        return new ModelAndView("login/list");
    }

    /**
     * 新增或者删除页面
     * @return
     */
    @GetMapping(path = "page/save")
    public ModelAndView pageSave() {
        return new ModelAndView("login/save");
    }

    /**
     * 新增或者更新接口
     * @param param
     * @return
     */
    @PostMapping(path = "save")
    public UpdateRestRes save(SecurityLoginEntity param) {
        return UpdateRestRes.builder().build();
    }

    @PostMapping(path = "delete/{id}")
    public UpdateRestRes delete(@PathVariable("id") Long id) {
        return UpdateRestRes.builder().build();
    }

    @GetMapping(path = "get/{id}")
    public SecurityLoginEntity get(@PathVariable("id") Long id) {
        return SecurityLoginEntity.builder().build();
    }

    @PostMapping(path = "list/{pageIndex}/{pageSize}")
    public PageRestRes list(@PathVariable("pageIndex") Long pageIndex, @PathVariable("pageSize") Long pageSize) {
        return PageRestRes.builder().build();
    }


}
