package com.springbootpractice.demo.demo_jpa.dao.entity;

import com.springbootpractice.demo.demo_jpa.dao.converter.SexConverter;
import com.springbootpractice.demo.demo_jpa.dao.entity.enums.SexEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 3:51 下午
 **/
@Entity(name = "userLogin")
@Table(name = "user_login")
@Data
public class UserLoginEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    private String password;

    @Convert(converter = SexConverter.class)
    private SexEnum sex;

    private String note;

}
