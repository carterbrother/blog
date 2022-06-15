package ${package}.biz.service.impl;

import ${package}.dal.mapper.SysUserMapper;
import ${package}.dal.model.SysUser;
import ${package}.biz.service.SysUserService;
import ${package}.biz.configurer.core.AbstractService;
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
