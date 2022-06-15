package com.xxx.demo.biz.service.impl;

import com.xxx.demo.dal.mapper.SysUserMenuMapper;
import com.xxx.demo.dal.model.SysUserMenu;
import com.xxx.demo.biz.service.SysUserMenuService;
import com.xxx.demo.biz.configurer.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/19.
 */
@Service
@Transactional
public class SysUserMenuServiceImpl extends AbstractService<SysUserMenu> implements SysUserMenuService {
    @Resource
    private SysUserMenuMapper sysUserMenuMapper;

}
