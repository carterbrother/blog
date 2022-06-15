package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.RoleUserConfig;
import com.springbootpractice.demo.easycode.plugin.dao.RoleUserConfigDao;
import com.springbootpractice.demo.easycode.plugin.service.RoleUserConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色用户对应关系(RoleUserConfig)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("roleUserConfigService")
public class RoleUserConfigServiceImpl implements RoleUserConfigService {
    @Resource
    private RoleUserConfigDao roleUserConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoleUserConfig queryById(Long id) {
        return this.roleUserConfigDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RoleUserConfig> queryAllByLimit(int offset, int limit) {
        return this.roleUserConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roleUserConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RoleUserConfig insert(RoleUserConfig roleUserConfig) {
        this.roleUserConfigDao.insert(roleUserConfig);
        return roleUserConfig;
    }

    /**
     * 修改数据
     *
     * @param roleUserConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RoleUserConfig update(RoleUserConfig roleUserConfig) {
        this.roleUserConfigDao.update(roleUserConfig);
        return this.queryById(roleUserConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.roleUserConfigDao.deleteById(id) > 0;
    }
}