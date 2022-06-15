package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.SystemDict;
import com.springbootpractice.demo.easycode.plugin.dao.SystemDictDao;
import com.springbootpractice.demo.easycode.plugin.service.SystemDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 接入的系统（按照实际的产品来分）(SystemDict)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("systemDictService")
public class SystemDictServiceImpl implements SystemDictService {
    @Resource
    private SystemDictDao systemDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SystemDict queryById(Long id) {
        return this.systemDictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SystemDict> queryAllByLimit(int offset, int limit) {
        return this.systemDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param systemDict 实例对象
     * @return 实例对象
     */
    @Override
    public SystemDict insert(SystemDict systemDict) {
        this.systemDictDao.insert(systemDict);
        return systemDict;
    }

    /**
     * 修改数据
     *
     * @param systemDict 实例对象
     * @return 实例对象
     */
    @Override
    public SystemDict update(SystemDict systemDict) {
        this.systemDictDao.update(systemDict);
        return this.queryById(systemDict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.systemDictDao.deleteById(id) > 0;
    }
}