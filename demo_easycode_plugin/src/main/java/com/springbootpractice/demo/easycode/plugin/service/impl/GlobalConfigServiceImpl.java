package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.GlobalConfig;
import com.springbootpractice.demo.easycode.plugin.dao.GlobalConfigDao;
import com.springbootpractice.demo.easycode.plugin.service.GlobalConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 全局配置(GlobalConfig)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("globalConfigService")
public class GlobalConfigServiceImpl implements GlobalConfigService {
    @Resource
    private GlobalConfigDao globalConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GlobalConfig queryById(Object id) {
        return this.globalConfigDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GlobalConfig> queryAllByLimit(int offset, int limit) {
        return this.globalConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param globalConfig 实例对象
     * @return 实例对象
     */
    @Override
    public GlobalConfig insert(GlobalConfig globalConfig) {
        this.globalConfigDao.insert(globalConfig);
        return globalConfig;
    }

    /**
     * 修改数据
     *
     * @param globalConfig 实例对象
     * @return 实例对象
     */
    @Override
    public GlobalConfig update(GlobalConfig globalConfig) {
        this.globalConfigDao.update(globalConfig);
        return this.queryById(globalConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.globalConfigDao.deleteById(id) > 0;
    }
}