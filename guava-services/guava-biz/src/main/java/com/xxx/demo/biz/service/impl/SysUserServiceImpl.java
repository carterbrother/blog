package com.xxx.demo.biz.service.impl;

import com.xxx.demo.dal.mapper.SysUserMapper;
import com.xxx.demo.dal.model.SysUser;
import com.xxx.demo.biz.service.SysUserService;
import com.xxx.demo.biz.configurer.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/19.
 */
@Service
@Transactional
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

}
