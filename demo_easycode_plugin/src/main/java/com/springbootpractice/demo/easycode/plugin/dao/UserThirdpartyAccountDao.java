package com.springbootpractice.demo.easycode.plugin.dao;

import com.springbootpractice.demo.easycode.plugin.entity.UserThirdpartyAccount;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 第三方账号(UserThirdpartyAccount)表数据库访问层
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public interface UserThirdpartyAccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserThirdpartyAccount queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserThirdpartyAccount> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userThirdpartyAccount 实例对象
     * @return 对象列表
     */
    List<UserThirdpartyAccount> queryAll(UserThirdpartyAccount userThirdpartyAccount);

    /**
     * 新增数据
     *
     * @param userThirdpartyAccount 实例对象
     * @return 影响行数
     */
    int insert(UserThirdpartyAccount userThirdpartyAccount);

    /**
     * 修改数据
     *
     * @param userThirdpartyAccount 实例对象
     * @return 影响行数
     */
    int update(UserThirdpartyAccount userThirdpartyAccount);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}