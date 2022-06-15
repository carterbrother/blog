package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.RoleDict;
import com.springbootpractice.demo.easycode.plugin.dao.RoleDictDao;
import com.springbootpractice.demo.easycode.plugin.service.RoleDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统角色(RoleDict)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("roleDictService")
public class RoleDictServiceImpl implements RoleDictService {
    @Resource
    private RoleDictDao roleDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoleDict queryById(Long id) {
        return this.roleDictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RoleDict> queryAllByLimit(int offset, int limit) {
        return this.roleDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roleDict 实例对象
     * @return 实例对象
     */
    @Override
    public RoleDict insert(RoleDict roleDict) {
        this.roleDictDao.insert(roleDict);
        return roleDict;
    }

    /**
     * 修改数据
     *
     * @param roleDict 实例对象
     * @return 实例对象
     */
    @Override
    public RoleDict update(RoleDict roleDict) {
        this.roleDictDao.update(roleDict);
        return this.queryById(roleDict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.roleDictDao.deleteById(id) > 0;
    }
}