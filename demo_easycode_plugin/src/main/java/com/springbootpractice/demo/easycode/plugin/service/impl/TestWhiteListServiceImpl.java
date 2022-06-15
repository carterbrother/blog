package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.TestWhiteList;
import com.springbootpractice.demo.easycode.plugin.dao.TestWhiteListDao;
import com.springbootpractice.demo.easycode.plugin.service.TestWhiteListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TestWhiteList)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("testWhiteListService")
public class TestWhiteListServiceImpl implements TestWhiteListService {
    @Resource
    private TestWhiteListDao testWhiteListDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TestWhiteList queryById(Object id) {
        return this.testWhiteListDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TestWhiteList> queryAllByLimit(int offset, int limit) {
        return this.testWhiteListDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param testWhiteList 实例对象
     * @return 实例对象
     */
    @Override
    public TestWhiteList insert(TestWhiteList testWhiteList) {
        this.testWhiteListDao.insert(testWhiteList);
        return testWhiteList;
    }

    /**
     * 修改数据
     *
     * @param testWhiteList 实例对象
     * @return 实例对象
     */
    @Override
    public TestWhiteList update(TestWhiteList testWhiteList) {
        this.testWhiteListDao.update(testWhiteList);
        return this.queryById(testWhiteList.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.testWhiteListDao.deleteById(id) > 0;
    }
}