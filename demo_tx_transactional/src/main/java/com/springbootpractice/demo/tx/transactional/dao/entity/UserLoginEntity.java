package com.springbootpractice.demo.tx.transactional.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* author: carter
* 对应数据库表： user_login
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginEntity {
    /**
    * 主键  对应数据库表字段: user_login.id
    */
    private Integer id;

    /**
    * 用户名  对应数据库表字段: user_login.user_name
    */
    private String userName;

    /**
    * 密码  对应数据库表字段: user_login.password
    */
    private String password;

    /**
    * 1男2女  对应数据库表字段: user_login.sex
    */
    private Byte sex;

    /**
    * 备注  对应数据库表字段: user_login.note
    */
    private String note;
}