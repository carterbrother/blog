package com.springbootpractice.demo.spring.security1.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: carter
* 对应数据库表： security_role
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityRoleEntity {
    /**
    * 自增id  对应数据库表字段: security_role.id
    */
    private Long id;

    /**
    * 应用id  对应数据库表字段: security_role.app_id
    */
    private Long appId;

    /**
    * 角色英文名称  对应数据库表字段: security_role.role_english_name
    */
    private String roleEnglishName;

    /**
    * 角色中文名称  对应数据库表字段: security_role.role_chinese_name
    */
    private String roleChineseName;
}