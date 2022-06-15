package com.springbootpractice.demo.mybatis.dao.mapper;

import com.springbootpractice.demo.mybatis.dao.entity.UserLoginEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 5:17 下午
 **/
@Repository
public interface UserLoginMapper {

    /**
     * 通过id查询用户信息
     * @param id id
     * @return 用户信息
     */
    UserLoginEntity getById(@Param("id") Long id);

}
