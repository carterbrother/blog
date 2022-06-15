package com.springbootpractice.demo.demo_jpa;

import com.springbootpractice.demo.demo_jpa.dao.entity.UserLoginEntity;
import com.springbootpractice.demo.demo_jpa.dao.repository.UserLoginRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
class DemoJpaApplicationTests {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Test
    void geUserLoginEntityByIdTest() {

        final Optional<UserLoginEntity> userLoginEntity = userLoginRepository.findById(1L);

        System.out.println(" userLoginEntity:"+userLoginEntity);

        Assert.isTrue(Objects.equals(1L,userLoginEntity.get().getId()),"查询成功");

    }

    @Test
    void queryTest() {
//        final List<UserLoginEntity> userLoginEntityList = userLoginRepository.findUserLogin("ca");
        final List<UserLoginEntity> userLoginEntityList2 = userLoginRepository.findByUserNameIsLike("ca");

//        System.out.println(userLoginEntityList);
        System.out.println("====");
        System.out.println(userLoginEntityList2);

//        Assert.isTrue(Objects.equals(userLoginEntityList,userLoginEntityList2),"查询结果不一致");
    }
}
