package com.xxx.demo.biz.service.impl;

import com.xxx.demo.dal.mapper.SysShortcutMenuMapper;
import com.xxx.demo.dal.model.SysShortcutMenu;
import com.xxx.demo.biz.service.SysShortcutMenuService;
import com.xxx.demo.biz.configurer.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/19.
 */
@Service
@Transactional
public class SysShortcutMenuServiceImpl extends AbstractService<SysShortcutMenu> implements SysShortcutMenuService {
    @Resource
    private SysShortcutMenuMapper sysShortcutMenuMapper;

}
