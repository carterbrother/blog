package com.springbootpractice.demo.mybatis.dao.entity;

import com.springbootpractice.demo.mybatis.dao.entity.enums.SexEnum;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 5:06 下午
 **/
@Data
@Alias("userLogin")
public class UserLoginEntity implements Serializable {

    private Long id;

    private String userName;

    private String password;

    private SexEnum sex;

    private String  note;

}
