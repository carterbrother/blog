package com.springbootpractice.demo.spring.security1.dao.mapper;

import com.springbootpractice.demo.spring.security1.dao.entity.SecurityLoginRoleEntity;
import com.springbootpractice.demo.spring.security1.dao.example.SecurityLoginRoleEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityLoginRoleEntityMapper {
    long countByExample(SecurityLoginRoleEntityExample example);

    int insert(SecurityLoginRoleEntity record);

    int insertSelective(SecurityLoginRoleEntity record);

    List<SecurityLoginRoleEntity> selectByExample(SecurityLoginRoleEntityExample example);

    SecurityLoginRoleEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SecurityLoginRoleEntity record, @Param("example") SecurityLoginRoleEntityExample example);

    int updateByExample(@Param("record") SecurityLoginRoleEntity record, @Param("example") SecurityLoginRoleEntityExample example);

    int updateByPrimaryKeySelective(SecurityLoginRoleEntity record);

    int updateByPrimaryKey(SecurityLoginRoleEntity record);
}