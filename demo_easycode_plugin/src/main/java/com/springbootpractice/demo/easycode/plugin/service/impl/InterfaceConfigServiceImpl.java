package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.InterfaceConfig;
import com.springbootpractice.demo.easycode.plugin.dao.InterfaceConfigDao;
import com.springbootpractice.demo.easycode.plugin.service.InterfaceConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 接口配置(InterfaceConfig)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("interfaceConfigService")
public class InterfaceConfigServiceImpl implements InterfaceConfigService {
    @Resource
    private InterfaceConfigDao interfaceConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public InterfaceConfig queryById(Long id) {
        return this.interfaceConfigDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<InterfaceConfig> queryAllByLimit(int offset, int limit) {
        return this.interfaceConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param interfaceConfig 实例对象
     * @return 实例对象
     */
    @Override
    public InterfaceConfig insert(InterfaceConfig interfaceConfig) {
        this.interfaceConfigDao.insert(interfaceConfig);
        return interfaceConfig;
    }

    /**
     * 修改数据
     *
     * @param interfaceConfig 实例对象
     * @return 实例对象
     */
    @Override
    public InterfaceConfig update(InterfaceConfig interfaceConfig) {
        this.interfaceConfigDao.update(interfaceConfig);
        return this.queryById(interfaceConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.interfaceConfigDao.deleteById(id) > 0;
    }
}