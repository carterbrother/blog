package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.TopicDict;
import com.springbootpractice.demo.easycode.plugin.dao.TopicDictDao;
import com.springbootpractice.demo.easycode.plugin.service.TopicDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 研究课题字典表(TopicDict)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
@Service("topicDictService")
public class TopicDictServiceImpl implements TopicDictService {
    @Resource
    private TopicDictDao topicDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TopicDict queryById(Long id) {
        return this.topicDictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TopicDict> queryAllByLimit(int offset, int limit) {
        return this.topicDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param topicDict 实例对象
     * @return 实例对象
     */
    @Override
    public TopicDict insert(TopicDict topicDict) {
        this.topicDictDao.insert(topicDict);
        return topicDict;
    }

    /**
     * 修改数据
     *
     * @param topicDict 实例对象
     * @return 实例对象
     */
    @Override
    public TopicDict update(TopicDict topicDict) {
        this.topicDictDao.update(topicDict);
        return this.queryById(topicDict.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.topicDictDao.deleteById(id) > 0;
    }
}