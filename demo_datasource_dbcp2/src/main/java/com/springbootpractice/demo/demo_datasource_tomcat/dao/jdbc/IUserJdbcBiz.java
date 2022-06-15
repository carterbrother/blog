package com.springbootpractice.demo.demo_datasource_tomcat.dao.jdbc;

import com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.UserLoginEntity;

import java.util.List;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 2:41 下午
 **/

public interface IUserJdbcBiz {
    UserLoginEntity getUserLogin(Long id);

    List<UserLoginEntity> findUserLogin(String userName, String note);

    long createUserLogin(UserLoginEntity entity);

    long updateUserLogin(UserLoginEntity entity);

    long deleteUserLogin(Long id);
}
