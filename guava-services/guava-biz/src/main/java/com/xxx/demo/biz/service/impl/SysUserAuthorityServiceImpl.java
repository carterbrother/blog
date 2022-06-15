package com.xxx.demo.biz.service.impl;

import com.xxx.demo.dal.mapper.SysUserAuthorityMapper;
import com.xxx.demo.dal.model.SysUserAuthority;
import com.xxx.demo.biz.service.SysUserAuthorityService;
import com.xxx.demo.biz.configurer.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/19.
 */
@Service
@Transactional
public class SysUserAuthorityServiceImpl extends AbstractService<SysUserAuthority> implements SysUserAuthorityService {
    @Resource
    private SysUserAuthorityMapper sysUserAuthorityMapper;

}
