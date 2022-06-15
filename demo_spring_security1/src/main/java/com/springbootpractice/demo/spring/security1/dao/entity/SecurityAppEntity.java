package com.springbootpractice.demo.spring.security1.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: carter
* 对应数据库表： security_app
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityAppEntity {
    /**
    * 自增id  对应数据库表字段: security_app.id
    */
    private Long id;

    /**
    * 应用中文名称  对应数据库表字段: security_app.app_chinese_name
    */
    private String appChineseName;

    /**
    * 备注  对应数据库表字段: security_app.note
    */
    private String note;
}