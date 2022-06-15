package com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 说明：性别枚举
 * @author carter
 * 创建时间： 2020年01月07日 2:32 下午
 **/
@Getter
@AllArgsConstructor
public enum SexEnum {

    MALE(1,"男"),
    FEMALE(2,"女");

    private Integer code;

    private String title;


    public static SexEnum getByCode(Integer code){
        return Arrays.stream(SexEnum.values())
                .filter(item-> Objects.equals(code,item.getCode()))
                .findFirst()
                .orElse(null);
    }

}
