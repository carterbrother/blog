package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.GrayStrategy;
import com.springbootpractice.demo.easycode.plugin.dao.GrayStrategyDao;
import com.springbootpractice.demo.easycode.plugin.service.GrayStrategyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 灰度策略(GrayStrategy)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("grayStrategyService")
public class GrayStrategyServiceImpl implements GrayStrategyService {
    @Resource
    private GrayStrategyDao grayStrategyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GrayStrategy queryById(Object id) {
        return this.grayStrategyDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GrayStrategy> queryAllByLimit(int offset, int limit) {
        return this.grayStrategyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param grayStrategy 实例对象
     * @return 实例对象
     */
    @Override
    public GrayStrategy insert(GrayStrategy grayStrategy) {
        this.grayStrategyDao.insert(grayStrategy);
        return grayStrategy;
    }

    /**
     * 修改数据
     *
     * @param grayStrategy 实例对象
     * @return 实例对象
     */
    @Override
    public GrayStrategy update(GrayStrategy grayStrategy) {
        this.grayStrategyDao.update(grayStrategy);
        return this.queryById(grayStrategy.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.grayStrategyDao.deleteById(id) > 0;
    }
}