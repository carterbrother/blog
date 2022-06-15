package com.springbootpractice.demo.spring.security1.dao.mapper;

import com.springbootpractice.demo.spring.security1.dao.entity.SecurityRoleEntity;
import com.springbootpractice.demo.spring.security1.dao.example.SecurityRoleEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRoleEntityMapper {
    long countByExample(SecurityRoleEntityExample example);

    int insert(SecurityRoleEntity record);

    int insertSelective(SecurityRoleEntity record);

    List<SecurityRoleEntity> selectByExample(SecurityRoleEntityExample example);

    SecurityRoleEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SecurityRoleEntity record, @Param("example") SecurityRoleEntityExample example);

    int updateByExample(@Param("record") SecurityRoleEntity record, @Param("example") SecurityRoleEntityExample example);

    int updateByPrimaryKeySelective(SecurityRoleEntity record);

    int updateByPrimaryKey(SecurityRoleEntity record);
}