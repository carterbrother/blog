package com.springbootpractice.demo.easycode.plugin.service;

import com.springbootpractice.demo.easycode.plugin.entity.GrayList;
import java.util.List;

/**
 * 灰度名单(GrayList)表服务接口
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public interface GrayListService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GrayList queryById(Object id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GrayList> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param grayList 实例对象
     * @return 实例对象
     */
    GrayList insert(GrayList grayList);

    /**
     * 修改数据
     *
     * @param grayList 实例对象
     * @return 实例对象
     */
    GrayList update(GrayList grayList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}