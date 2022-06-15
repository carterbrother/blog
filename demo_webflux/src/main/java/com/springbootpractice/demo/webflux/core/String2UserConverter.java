package com.springbootpractice.demo.webflux.core;

import com.springbootpractice.demo.webflux.dao.entity.User;
import com.springbootpractice.demo.webflux.dao.entity.enums.SexEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月16日 9:28 上午
 **/

public class String2UserConverter implements Converter<String, User> {
    @Override
    public User convert(String s) {
        final String[] split = Objects.requireNonNull(s,"转换为User的string不能为空").split("-");

        Assert.isTrue(split.length==4,"转换为User的string必须含有4个字段");
        return User.builder()
                .id(Long.parseLong(split[0]))
                .userName(split[1])
                .note(split[2])
                .sex(SexEnum.getSexEnum(Integer.parseInt(split[3])))
                .build();
    }
}
