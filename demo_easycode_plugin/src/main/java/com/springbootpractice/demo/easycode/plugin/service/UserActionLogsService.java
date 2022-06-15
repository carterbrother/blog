package com.springbootpractice.demo.easycode.plugin.service;

import com.springbootpractice.demo.easycode.plugin.entity.UserActionLogs;
import java.util.List;

/**
 * (UserActionLogs)表服务接口
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public interface UserActionLogsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserActionLogs queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserActionLogs> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userActionLogs 实例对象
     * @return 实例对象
     */
    UserActionLogs insert(UserActionLogs userActionLogs);

    /**
     * 修改数据
     *
     * @param userActionLogs 实例对象
     * @return 实例对象
     */
    UserActionLogs update(UserActionLogs userActionLogs);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}