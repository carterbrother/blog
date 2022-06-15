package com.springbootpractice.demo.easycode.plugin.dao;

import com.springbootpractice.demo.easycode.plugin.entity.OrgDoctorConfig;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 机构跟医生的关系(OrgDoctorConfig)表数据库访问层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public interface OrgDoctorConfigDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrgDoctorConfig queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrgDoctorConfig> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orgDoctorConfig 实例对象
     * @return 对象列表
     */
    List<OrgDoctorConfig> queryAll(OrgDoctorConfig orgDoctorConfig);

    /**
     * 新增数据
     *
     * @param orgDoctorConfig 实例对象
     * @return 影响行数
     */
    int insert(OrgDoctorConfig orgDoctorConfig);

    /**
     * 修改数据
     *
     * @param orgDoctorConfig 实例对象
     * @return 影响行数
     */
    int update(OrgDoctorConfig orgDoctorConfig);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}