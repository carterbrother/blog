package com.springbootpractice.demo.easycode.plugin.service;

import com.springbootpractice.demo.easycode.plugin.entity.PageDict;
import java.util.List;

/**
 * 页面字典(对应后台系统的导航条)(PageDict)表服务接口
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public interface PageDictService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PageDict queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PageDict> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param pageDict 实例对象
     * @return 实例对象
     */
    PageDict insert(PageDict pageDict);

    /**
     * 修改数据
     *
     * @param pageDict 实例对象
     * @return 实例对象
     */
    PageDict update(PageDict pageDict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}