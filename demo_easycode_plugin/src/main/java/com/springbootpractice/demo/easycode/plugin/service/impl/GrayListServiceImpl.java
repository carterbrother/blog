package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.GrayList;
import com.springbootpractice.demo.easycode.plugin.dao.GrayListDao;
import com.springbootpractice.demo.easycode.plugin.service.GrayListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 灰度名单(GrayList)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("grayListService")
public class GrayListServiceImpl implements GrayListService {
    @Resource
    private GrayListDao grayListDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GrayList queryById(Object id) {
        return this.grayListDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GrayList> queryAllByLimit(int offset, int limit) {
        return this.grayListDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param grayList 实例对象
     * @return 实例对象
     */
    @Override
    public GrayList insert(GrayList grayList) {
        this.grayListDao.insert(grayList);
        return grayList;
    }

    /**
     * 修改数据
     *
     * @param grayList 实例对象
     * @return 实例对象
     */
    @Override
    public GrayList update(GrayList grayList) {
        this.grayListDao.update(grayList);
        return this.queryById(grayList.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object id) {
        return this.grayListDao.deleteById(id) > 0;
    }
}