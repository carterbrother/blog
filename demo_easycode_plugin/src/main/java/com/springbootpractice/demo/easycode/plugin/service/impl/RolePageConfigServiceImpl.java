package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.RolePageConfig;
import com.springbootpractice.demo.easycode.plugin.dao.RolePageConfigDao;
import com.springbootpractice.demo.easycode.plugin.service.RolePageConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色页面关联表(RolePageConfig)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("rolePageConfigService")
public class RolePageConfigServiceImpl implements RolePageConfigService {
    @Resource
    private RolePageConfigDao rolePageConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RolePageConfig queryById(Long id) {
        return this.rolePageConfigDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RolePageConfig> queryAllByLimit(int offset, int limit) {
        return this.rolePageConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param rolePageConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RolePageConfig insert(RolePageConfig rolePageConfig) {
        this.rolePageConfigDao.insert(rolePageConfig);
        return rolePageConfig;
    }

    /**
     * 修改数据
     *
     * @param rolePageConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RolePageConfig update(RolePageConfig rolePageConfig) {
        this.rolePageConfigDao.update(rolePageConfig);
        return this.queryById(rolePageConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.rolePageConfigDao.deleteById(id) > 0;
    }
}