package com.springbootpractice.demo.mybatis;

import com.springbootpractice.demo.mybatis.dao.entity.UserLoginEntity;
import com.springbootpractice.demo.mybatis.dao.mapper.UserLoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Objects;

@SpringBootTest
class DemoMybatisApplicationTests {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Test
    void userLoginMapperTest() {

        final UserLoginEntity userLoginEntity = userLoginMapper.getById(1L);

        System.out.println(userLoginEntity);

        Assert.isTrue(Objects.equals(1L,userLoginEntity.getId()),"mapper方法getById失败");

    }

}
