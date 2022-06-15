package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.PageDict;
import com.springbootpractice.demo.easycode.plugin.dao.PageDictDao;
import com.springbootpractice.demo.easycode.plugin.service.PageDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 页面字典(对应后台系统的导航条)(PageDict)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("pageDictService")
public class PageDictServiceImpl implements PageDictService {
    @Resource
    private PageDictDao pageDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PageDict queryById(Long id) {
        return this.pageDictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<PageDict> queryAllByLimit(int offset, int limit) {
        return this.pageDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param pageDict 实例对象
     * @return 实例对象
     */
    @Override
    public PageDict insert(PageDict pageDict) {
        this.pageDictDao.insert(pageDict);
        return pageDict;
    }

    /**
     * 修改数据
     *
     * @param pageDict 实例对象
     * @return 实例对象
     */
    @Override
    public PageDict update(PageDict pageDict) {
        this.pageDictDao.update(pageDict);
        return this.queryById(pageDict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.pageDictDao.deleteById(id) > 0;
    }
}