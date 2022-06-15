package com.springbootpractice.demo.spring.security1.dao.mapper;

import com.springbootpractice.demo.spring.security1.dao.entity.SecurityLoginEntity;
import com.springbootpractice.demo.spring.security1.dao.example.SecurityLoginEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityLoginEntityMapper {
    long countByExample(SecurityLoginEntityExample example);

    int insert(SecurityLoginEntity record);

    int insertSelective(SecurityLoginEntity record);

    List<SecurityLoginEntity> selectByExample(SecurityLoginEntityExample example);

    SecurityLoginEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SecurityLoginEntity record, @Param("example") SecurityLoginEntityExample example);

    int updateByExample(@Param("record") SecurityLoginEntity record, @Param("example") SecurityLoginEntityExample example);

    int updateByPrimaryKeySelective(SecurityLoginEntity record);

    int updateByPrimaryKey(SecurityLoginEntity record);
}