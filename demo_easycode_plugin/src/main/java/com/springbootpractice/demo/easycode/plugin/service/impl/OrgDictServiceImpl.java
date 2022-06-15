package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.OrgDict;
import com.springbootpractice.demo.easycode.plugin.dao.OrgDictDao;
import com.springbootpractice.demo.easycode.plugin.service.OrgDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机构或者医院(OrgDict)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("orgDictService")
public class OrgDictServiceImpl implements OrgDictService {
    @Resource
    private OrgDictDao orgDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrgDict queryById(Long id) {
        return this.orgDictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OrgDict> queryAllByLimit(int offset, int limit) {
        return this.orgDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orgDict 实例对象
     * @return 实例对象
     */
    @Override
    public OrgDict insert(OrgDict orgDict) {
        this.orgDictDao.insert(orgDict);
        return orgDict;
    }

    /**
     * 修改数据
     *
     * @param orgDict 实例对象
     * @return 实例对象
     */
    @Override
    public OrgDict update(OrgDict orgDict) {
        this.orgDictDao.update(orgDict);
        return this.queryById(orgDict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orgDictDao.deleteById(id) > 0;
    }
}