package com.springbootpractice.demo.spring.security1.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: carter
* 对应数据库表： security_login_role
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityLoginRoleEntity {
    /**
    * 自增id  对应数据库表字段: security_login_role.id
    */
    private Long id;

    /**
    * 登录id  对应数据库表字段: security_login_role.login_id
    */
    private Long loginId;

    /**
    * 角色id  对应数据库表字段: security_login_role.role_id
    */
    private Long roleId;

    /**
    * 创建日期  对应数据库表字段: security_login_role.created
    */
    private LocalDateTime created;

    /**
    * 更新日期  对应数据库表字段: security_login_role.updated
    */
    private LocalDateTime updated;
}