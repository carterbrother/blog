package com.springbootpractice.demo.demo_datasource_tomcat.dao.entity;

import com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 说明：用户登录实体
 * @author carter
 * 创建时间： 2020年01月07日 2:30 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginEntity implements Serializable {
    private static final long serialVersionUID = 5208671935373976853L;

    private Long id;

    private String userName;

    private String password;

    private SexEnum sex;

    private String note;

}
