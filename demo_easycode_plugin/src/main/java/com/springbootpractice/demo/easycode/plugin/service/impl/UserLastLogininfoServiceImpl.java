package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.UserLastLogininfo;
import com.springbootpractice.demo.easycode.plugin.dao.UserLastLogininfoDao;
import com.springbootpractice.demo.easycode.plugin.service.UserLastLogininfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户最后登录信息(UserLastLogininfo)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("userLastLogininfoService")
public class UserLastLogininfoServiceImpl implements UserLastLogininfoService {
    @Resource
    private UserLastLogininfoDao userLastLogininfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserLastLogininfo queryById(Long id) {
        return this.userLastLogininfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserLastLogininfo> queryAllByLimit(int offset, int limit) {
        return this.userLastLogininfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userLastLogininfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserLastLogininfo insert(UserLastLogininfo userLastLogininfo) {
        this.userLastLogininfoDao.insert(userLastLogininfo);
        return userLastLogininfo;
    }

    /**
     * 修改数据
     *
     * @param userLastLogininfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserLastLogininfo update(UserLastLogininfo userLastLogininfo) {
        this.userLastLogininfoDao.update(userLastLogininfo);
        return this.queryById(userLastLogininfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userLastLogininfoDao.deleteById(id) > 0;
    }
}