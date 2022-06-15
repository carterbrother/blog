package com.springbootpractice.demo.easycode.plugin.service;

import com.springbootpractice.demo.easycode.plugin.entity.DoctorTopic;
import java.util.List;

/**
 * 医生跟topic的绑定关系(DoctorTopic)表服务接口
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public interface DoctorTopicService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DoctorTopic queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DoctorTopic> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param doctorTopic 实例对象
     * @return 实例对象
     */
    DoctorTopic insert(DoctorTopic doctorTopic);

    /**
     * 修改数据
     *
     * @param doctorTopic 实例对象
     * @return 实例对象
     */
    DoctorTopic update(DoctorTopic doctorTopic);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}