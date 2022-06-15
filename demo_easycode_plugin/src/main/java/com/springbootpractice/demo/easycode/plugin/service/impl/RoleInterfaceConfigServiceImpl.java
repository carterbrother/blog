package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.RoleInterfaceConfig;
import com.springbootpractice.demo.easycode.plugin.dao.RoleInterfaceConfigDao;
import com.springbootpractice.demo.easycode.plugin.service.RoleInterfaceConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限项对应关系(RoleInterfaceConfig)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("roleInterfaceConfigService")
public class RoleInterfaceConfigServiceImpl implements RoleInterfaceConfigService {
    @Resource
    private RoleInterfaceConfigDao roleInterfaceConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoleInterfaceConfig queryById(Long id) {
        return this.roleInterfaceConfigDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RoleInterfaceConfig> queryAllByLimit(int offset, int limit) {
        return this.roleInterfaceConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roleInterfaceConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RoleInterfaceConfig insert(RoleInterfaceConfig roleInterfaceConfig) {
        this.roleInterfaceConfigDao.insert(roleInterfaceConfig);
        return roleInterfaceConfig;
    }

    /**
     * 修改数据
     *
     * @param roleInterfaceConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RoleInterfaceConfig update(RoleInterfaceConfig roleInterfaceConfig) {
        this.roleInterfaceConfigDao.update(roleInterfaceConfig);
        return this.queryById(roleInterfaceConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.roleInterfaceConfigDao.deleteById(id) > 0;
    }
}