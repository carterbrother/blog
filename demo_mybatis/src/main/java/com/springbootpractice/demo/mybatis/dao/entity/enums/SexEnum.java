package com.springbootpractice.demo.mybatis.dao.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 5:08 下午
 **/
@AllArgsConstructor
@Getter
public enum SexEnum {

    MALE(1,"男"),
    FEMALE(2,"女");

    private Integer code;

    private String title;


    public static SexEnum getByCode(Integer code){
        return Arrays.stream(SexEnum.values())
                .filter(item-> Objects.equals(code,item.code))
                .findFirst()
                .orElse(null);
    }


}
