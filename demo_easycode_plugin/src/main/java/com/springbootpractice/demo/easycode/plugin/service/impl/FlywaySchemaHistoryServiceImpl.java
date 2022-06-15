package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.FlywaySchemaHistory;
import com.springbootpractice.demo.easycode.plugin.dao.FlywaySchemaHistoryDao;
import com.springbootpractice.demo.easycode.plugin.service.FlywaySchemaHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FlywaySchemaHistory)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("flywaySchemaHistoryService")
public class FlywaySchemaHistoryServiceImpl implements FlywaySchemaHistoryService {
    @Resource
    private FlywaySchemaHistoryDao flywaySchemaHistoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param installedRank 主键
     * @return 实例对象
     */
    @Override
    public FlywaySchemaHistory queryById(Integer installedRank) {
        return this.flywaySchemaHistoryDao.queryById(installedRank);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FlywaySchemaHistory> queryAllByLimit(int offset, int limit) {
        return this.flywaySchemaHistoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param flywaySchemaHistory 实例对象
     * @return 实例对象
     */
    @Override
    public FlywaySchemaHistory insert(FlywaySchemaHistory flywaySchemaHistory) {
        this.flywaySchemaHistoryDao.insert(flywaySchemaHistory);
        return flywaySchemaHistory;
    }

    /**
     * 修改数据
     *
     * @param flywaySchemaHistory 实例对象
     * @return 实例对象
     */
    @Override
    public FlywaySchemaHistory update(FlywaySchemaHistory flywaySchemaHistory) {
        this.flywaySchemaHistoryDao.update(flywaySchemaHistory);
        return this.queryById(flywaySchemaHistory.getInstalledRank());
    }

    /**
     * 通过主键删除数据
     *
     * @param installedRank 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer installedRank) {
        return this.flywaySchemaHistoryDao.deleteById(installedRank) > 0;
    }
}