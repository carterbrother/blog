package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.UserActionLogs;
import com.springbootpractice.demo.easycode.plugin.dao.UserActionLogsDao;
import com.springbootpractice.demo.easycode.plugin.service.UserActionLogsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserActionLogs)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("userActionLogsService")
public class UserActionLogsServiceImpl implements UserActionLogsService {
    @Resource
    private UserActionLogsDao userActionLogsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserActionLogs queryById(Integer id) {
        return this.userActionLogsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserActionLogs> queryAllByLimit(int offset, int limit) {
        return this.userActionLogsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userActionLogs 实例对象
     * @return 实例对象
     */
    @Override
    public UserActionLogs insert(UserActionLogs userActionLogs) {
        this.userActionLogsDao.insert(userActionLogs);
        return userActionLogs;
    }

    /**
     * 修改数据
     *
     * @param userActionLogs 实例对象
     * @return 实例对象
     */
    @Override
    public UserActionLogs update(UserActionLogs userActionLogs) {
        this.userActionLogsDao.update(userActionLogs);
        return this.queryById(userActionLogs.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userActionLogsDao.deleteById(id) > 0;
    }
}