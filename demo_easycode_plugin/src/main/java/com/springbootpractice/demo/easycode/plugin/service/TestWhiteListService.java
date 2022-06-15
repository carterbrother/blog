package com.springbootpractice.demo.easycode.plugin.service;

import com.springbootpractice.demo.easycode.plugin.entity.TestWhiteList;
import java.util.List;

/**
 * (TestWhiteList)表服务接口
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public interface TestWhiteListService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestWhiteList queryById(Object id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TestWhiteList> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param testWhiteList 实例对象
     * @return 实例对象
     */
    TestWhiteList insert(TestWhiteList testWhiteList);

    /**
     * 修改数据
     *
     * @param testWhiteList 实例对象
     * @return 实例对象
     */
    TestWhiteList update(TestWhiteList testWhiteList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}