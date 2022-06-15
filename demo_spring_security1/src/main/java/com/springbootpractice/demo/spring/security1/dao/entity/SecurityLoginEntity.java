package com.springbootpractice.demo.spring.security1.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: carter
* 对应数据库表： security_login
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityLoginEntity {
    /**
    * 自增id  对应数据库表字段: security_login.id
    */
    private Long id;

    /**
    * 用户名  对应数据库表字段: security_login.username
    */
    private String username;

    /**
    * 密码  对应数据库表字段: security_login.password
    */
    private String password;

    /**
    * 密码过期时间  对应数据库表字段: security_login.password_expire_date
    */
    private LocalDateTime passwordExpireDate;

    /**
    * 锁定状态0锁定1正常  对应数据库表字段: security_login.lock_flag
    */
    private Integer lockFlag;

    /**
    * 可用状态1可用0不可用  对应数据库表字段: security_login.enable_flag
    */
    private Integer enableFlag;

    /**
    * 创建日期  对应数据库表字段: security_login.created
    */
    private LocalDateTime created;

    /**
    * 更新日期  对应数据库表字段: security_login.updated
    */
    private LocalDateTime updated;
}