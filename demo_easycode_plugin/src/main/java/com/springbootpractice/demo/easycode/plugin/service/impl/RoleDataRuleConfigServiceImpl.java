package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.RoleDataRuleConfig;
import com.springbootpractice.demo.easycode.plugin.dao.RoleDataRuleConfigDao;
import com.springbootpractice.demo.easycode.plugin.service.RoleDataRuleConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限项对应关系(RoleDataRuleConfig)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("roleDataRuleConfigService")
public class RoleDataRuleConfigServiceImpl implements RoleDataRuleConfigService {
    @Resource
    private RoleDataRuleConfigDao roleDataRuleConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoleDataRuleConfig queryById(Long id) {
        return this.roleDataRuleConfigDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RoleDataRuleConfig> queryAllByLimit(int offset, int limit) {
        return this.roleDataRuleConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roleDataRuleConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RoleDataRuleConfig insert(RoleDataRuleConfig roleDataRuleConfig) {
        this.roleDataRuleConfigDao.insert(roleDataRuleConfig);
        return roleDataRuleConfig;
    }

    /**
     * 修改数据
     *
     * @param roleDataRuleConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RoleDataRuleConfig update(RoleDataRuleConfig roleDataRuleConfig) {
        this.roleDataRuleConfigDao.update(roleDataRuleConfig);
        return this.queryById(roleDataRuleConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.roleDataRuleConfigDao.deleteById(id) > 0;
    }
}