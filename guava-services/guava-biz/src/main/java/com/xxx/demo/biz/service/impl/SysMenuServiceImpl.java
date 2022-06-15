package com.xxx.demo.biz.service.impl;

import com.xxx.demo.dal.mapper.SysMenuMapper;
import com.xxx.demo.dal.model.SysMenu;
import com.xxx.demo.biz.service.SysMenuService;
import com.xxx.demo.biz.configurer.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/19.
 */
@Service
@Transactional
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

}
