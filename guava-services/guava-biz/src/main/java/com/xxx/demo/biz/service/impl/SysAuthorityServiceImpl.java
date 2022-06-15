package com.xxx.demo.biz.service.impl;

import com.xxx.demo.dal.mapper.SysAuthorityMapper;
import com.xxx.demo.dal.model.SysAuthority;
import com.xxx.demo.biz.service.SysAuthorityService;
import com.xxx.demo.biz.configurer.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/19.
 */
@Service
@Transactional
public class SysAuthorityServiceImpl extends AbstractService<SysAuthority> implements SysAuthorityService {
    @Resource
    private SysAuthorityMapper sysAuthorityMapper;

}
