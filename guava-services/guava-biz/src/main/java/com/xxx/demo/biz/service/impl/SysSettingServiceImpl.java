package com.xxx.demo.biz.service.impl;

import com.xxx.demo.dal.mapper.SysSettingMapper;
import com.xxx.demo.dal.model.SysSetting;
import com.xxx.demo.biz.service.SysSettingService;
import com.xxx.demo.biz.configurer.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/19.
 */
@Service
@Transactional
public class SysSettingServiceImpl extends AbstractService<SysSetting> implements SysSettingService {
    @Resource
    private SysSettingMapper sysSettingMapper;

}
