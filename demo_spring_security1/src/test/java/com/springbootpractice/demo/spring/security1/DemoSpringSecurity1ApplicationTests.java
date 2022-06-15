package com.springbootpractice.demo.spring.security1;

import com.springbootpractice.demo.spring.security1.dao.entity.SecurityLoginEntity;
import com.springbootpractice.demo.spring.security1.dao.mapper.SecurityLoginEntityMapper;
import com.springbootpractice.demo.spring.security1.dao.mapper.SecurityLoginRoleEntityMapper;
import com.springbootpractice.demo.spring.security1.dao.mapper.SecurityRoleEntityMapper;
import com.springbootpractice.demo.spring.security1.security.MyPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Security;
import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class DemoSpringSecurity1ApplicationTests {


    @Autowired
    private SecurityLoginEntityMapper securityLoginEntityMapper;

//    @Autowired
//    private SecurityLoginRoleEntityMapper securityLoginRoleEntityMapper;
//
//    @Autowired
//    private SecurityRoleEntityMapper securityRoleEntityMapper;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Test
    void insertTestLogin() {

        final SecurityLoginEntity securityLoginEntity = SecurityLoginEntity.builder()
                .username("lifuchun")
                .password(myPasswordEncoder.encode("abc123456"))
                .enableFlag(1)
                .lockFlag(1)
                .passwordExpireDate(LocalDateTime.now().plusYears(1))
                .build();
        securityLoginEntityMapper.insertSelective(securityLoginEntity);

        long id = securityLoginEntity.getId();

        final SecurityLoginEntity securityLoginEntityQuery = securityLoginEntityMapper.selectByPrimaryKey(id);

        log.info("insert:{}",securityLoginEntity);
        log.info("query:{}",securityLoginEntityQuery);


    }

    @Test
    void passwordTest() {
        System.out.println(myPasswordEncoder.encode("abc123456"));
    }
}
