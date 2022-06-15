package com.springbootpractice.demo.easycode.plugin.service.impl;

import com.springbootpractice.demo.easycode.plugin.entity.DoctorTopic;
import com.springbootpractice.demo.easycode.plugin.dao.DoctorTopicDao;
import com.springbootpractice.demo.easycode.plugin.service.DoctorTopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医生跟topic的绑定关系(DoctorTopic)表服务实现类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
@Service("doctorTopicService")
public class DoctorTopicServiceImpl implements DoctorTopicService {
    @Resource
    private DoctorTopicDao doctorTopicDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DoctorTopic queryById(Long id) {
        return this.doctorTopicDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DoctorTopic> queryAllByLimit(int offset, int limit) {
        return this.doctorTopicDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param doctorTopic 实例对象
     * @return 实例对象
     */
    @Override
    public DoctorTopic insert(DoctorTopic doctorTopic) {
        this.doctorTopicDao.insert(doctorTopic);
        return doctorTopic;
    }

    /**
     * 修改数据
     *
     * @param doctorTopic 实例对象
     * @return 实例对象
     */
    @Override
    public DoctorTopic update(DoctorTopic doctorTopic) {
        this.doctorTopicDao.update(doctorTopic);
        return this.queryById(doctorTopic.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.doctorTopicDao.deleteById(id) > 0;
    }
}