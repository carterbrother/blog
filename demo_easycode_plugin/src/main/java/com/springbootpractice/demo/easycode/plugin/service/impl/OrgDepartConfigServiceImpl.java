package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.OrgDepartConfig;
import com.springbootpractice.demo.easycode.plugin.dao.OrgDepartConfigDao;
import com.springbootpractice.demo.easycode.plugin.service.OrgDepartConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司的部门配置(OrgDepartConfig)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("orgDepartConfigService")
public class OrgDepartConfigServiceImpl implements OrgDepartConfigService {
    @Resource
    private OrgDepartConfigDao orgDepartConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrgDepartConfig queryById(Long id) {
        return this.orgDepartConfigDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OrgDepartConfig> queryAllByLimit(int offset, int limit) {
        return this.orgDepartConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orgDepartConfig 实例对象
     * @return 实例对象
     */
    @Override
    public OrgDepartConfig insert(OrgDepartConfig orgDepartConfig) {
        this.orgDepartConfigDao.insert(orgDepartConfig);
        return orgDepartConfig;
    }

    /**
     * 修改数据
     *
     * @param orgDepartConfig 实例对象
     * @return 实例对象
     */
    @Override
    public OrgDepartConfig update(OrgDepartConfig orgDepartConfig) {
        this.orgDepartConfigDao.update(orgDepartConfig);
        return this.queryById(orgDepartConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orgDepartConfigDao.deleteById(id) > 0;
    }
}