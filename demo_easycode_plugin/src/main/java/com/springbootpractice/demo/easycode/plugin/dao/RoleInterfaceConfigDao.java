package com.springbootpractice.demo.easycode.plugin.dao;

import com.springbootpractice.demo.easycode.plugin.entity.RoleInterfaceConfig;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 角色权限项对应关系(RoleInterfaceConfig)表数据库访问层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public interface RoleInterfaceConfigDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoleInterfaceConfig queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RoleInterfaceConfig> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param roleInterfaceConfig 实例对象
     * @return 对象列表
     */
    List<RoleInterfaceConfig> queryAll(RoleInterfaceConfig roleInterfaceConfig);

    /**
     * 新增数据
     *
     * @param roleInterfaceConfig 实例对象
     * @return 影响行数
     */
    int insert(RoleInterfaceConfig roleInterfaceConfig);

    /**
     * 修改数据
     *
     * @param roleInterfaceConfig 实例对象
     * @return 影响行数
     */
    int update(RoleInterfaceConfig roleInterfaceConfig);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}