package com.springbootpractice.demo.easycode.plugin.service;

import com.springbootpractice.demo.easycode.plugin.entity.SystemDict;
import java.util.List;

/**
 * 接入的系统（按照实际的产品来分）(SystemDict)表服务接口
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public interface SystemDictService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemDict queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SystemDict> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param systemDict 实例对象
     * @return 实例对象
     */
    SystemDict insert(SystemDict systemDict);

    /**
     * 修改数据
     *
     * @param systemDict 实例对象
     * @return 实例对象
     */
    SystemDict update(SystemDict systemDict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}