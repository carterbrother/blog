package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.UserCardBindHis;
import com.springbootpractice.demo.easycode.plugin.dao.UserCardBindHisDao;
import com.springbootpractice.demo.easycode.plugin.service.UserCardBindHisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserCardBindHis)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("userCardBindHisService")
public class UserCardBindHisServiceImpl implements UserCardBindHisService {
    @Resource
    private UserCardBindHisDao userCardBindHisDao;

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    @Override
    public UserCardBindHis queryById(Integer logId) {
        return this.userCardBindHisDao.queryById(logId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserCardBindHis> queryAllByLimit(int offset, int limit) {
        return this.userCardBindHisDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userCardBindHis 实例对象
     * @return 实例对象
     */
    @Override
    public UserCardBindHis insert(UserCardBindHis userCardBindHis) {
        this.userCardBindHisDao.insert(userCardBindHis);
        return userCardBindHis;
    }

    /**
     * 修改数据
     *
     * @param userCardBindHis 实例对象
     * @return 实例对象
     */
    @Override
    public UserCardBindHis update(UserCardBindHis userCardBindHis) {
        this.userCardBindHisDao.update(userCardBindHis);
        return this.queryById(userCardBindHis.getLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer logId) {
        return this.userCardBindHisDao.deleteById(logId) > 0;
    }
}