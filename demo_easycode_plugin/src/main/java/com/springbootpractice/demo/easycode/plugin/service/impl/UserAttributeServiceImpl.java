package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.UserAttribute;
import com.springbootpractice.demo.easycode.plugin.dao.UserAttributeDao;
import com.springbootpractice.demo.easycode.plugin.service.UserAttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户属性表(UserAttribute)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("userAttributeService")
public class UserAttributeServiceImpl implements UserAttributeService {
    @Resource
    private UserAttributeDao userAttributeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserAttribute queryById(Integer id) {
        return this.userAttributeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserAttribute> queryAllByLimit(int offset, int limit) {
        return this.userAttributeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userAttribute 实例对象
     * @return 实例对象
     */
    @Override
    public UserAttribute insert(UserAttribute userAttribute) {
        this.userAttributeDao.insert(userAttribute);
        return userAttribute;
    }

    /**
     * 修改数据
     *
     * @param userAttribute 实例对象
     * @return 实例对象
     */
    @Override
    public UserAttribute update(UserAttribute userAttribute) {
        this.userAttributeDao.update(userAttribute);
        return this.queryById(userAttribute.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userAttributeDao.deleteById(id) > 0;
    }
}