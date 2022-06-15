package com.springbootpractice.demo.easycode.plugin.dao;

import com.springbootpractice.demo.easycode.plugin.entity.FlywaySchemaHistory;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (FlywaySchemaHistory)表数据库访问层
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public interface FlywaySchemaHistoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param installedRank 主键
     * @return 实例对象
     */
    FlywaySchemaHistory queryById(Integer installedRank);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FlywaySchemaHistory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param flywaySchemaHistory 实例对象
     * @return 对象列表
     */
    List<FlywaySchemaHistory> queryAll(FlywaySchemaHistory flywaySchemaHistory);

    /**
     * 新增数据
     *
     * @param flywaySchemaHistory 实例对象
     * @return 影响行数
     */
    int insert(FlywaySchemaHistory flywaySchemaHistory);

    /**
     * 修改数据
     *
     * @param flywaySchemaHistory 实例对象
     * @return 影响行数
     */
    int update(FlywaySchemaHistory flywaySchemaHistory);

    /**
     * 通过主键删除数据
     *
     * @param installedRank 主键
     * @return 影响行数
     */
    int deleteById(Integer installedRank);

}