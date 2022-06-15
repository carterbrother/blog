package com.authine.cloudpivot.app.flyway.repository.impl;

import  com.authine.cloudpivot.app.flyway.repository.mapper.ResourceWebServerDOMapper;
import  com.authine.cloudpivot.app.flyway.repository.databaseobject.ResourceWebServerDO;
import  com.authine.cloudpivot.app.flyway.repository.ResourceWebServerDORepository;
import com.authine.mvp.lib.mybatis.core.AbstractRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/06/24.
 */
@Service
@Transactional
public class ResourceWebServerDORepositoryImpl extends AbstractRepository<ResourceWebServerDO> implements ResourceWebServerDORepository {
    @Resource
    private ResourceWebServerDOMapper resourceWebServerMapper;

}
