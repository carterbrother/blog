package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.DataRuleDict;
import com.springbootpractice.demo.easycode.plugin.dao.DataRuleDictDao;
import com.springbootpractice.demo.easycode.plugin.service.DataRuleDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据权限规则字典(DataRuleDict)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("dataRuleDictService")
public class DataRuleDictServiceImpl implements DataRuleDictService {
    @Resource
    private DataRuleDictDao dataRuleDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DataRuleDict queryById(Long id) {
        return this.dataRuleDictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DataRuleDict> queryAllByLimit(int offset, int limit) {
        return this.dataRuleDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dataRuleDict 实例对象
     * @return 实例对象
     */
    @Override
    public DataRuleDict insert(DataRuleDict dataRuleDict) {
        this.dataRuleDictDao.insert(dataRuleDict);
        return dataRuleDict;
    }

    /**
     * 修改数据
     *
     * @param dataRuleDict 实例对象
     * @return 实例对象
     */
    @Override
    public DataRuleDict update(DataRuleDict dataRuleDict) {
        this.dataRuleDictDao.update(dataRuleDict);
        return this.queryById(dataRuleDict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dataRuleDictDao.deleteById(id) > 0;
    }
}