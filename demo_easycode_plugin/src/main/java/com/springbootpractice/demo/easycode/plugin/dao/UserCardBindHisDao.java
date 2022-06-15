package com.springbootpractice.demo.easycode.plugin.dao;

import com.springbootpractice.demo.easycode.plugin.entity.UserCardBindHis;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserCardBindHis)表数据库访问层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public interface UserCardBindHisDao {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    UserCardBindHis queryById(Integer logId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserCardBindHis> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userCardBindHis 实例对象
     * @return 对象列表
     */
    List<UserCardBindHis> queryAll(UserCardBindHis userCardBindHis);

    /**
     * 新增数据
     *
     * @param userCardBindHis 实例对象
     * @return 影响行数
     */
    int insert(UserCardBindHis userCardBindHis);

    /**
     * 修改数据
     *
     * @param userCardBindHis 实例对象
     * @return 影响行数
     */
    int update(UserCardBindHis userCardBindHis);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 影响行数
     */
    int deleteById(Integer logId);

}