package com.springbootpractice.demo.spring.security1.dao.mapper;

import com.springbootpractice.demo.spring.security1.dao.entity.SecurityAppEntity;
import com.springbootpractice.demo.spring.security1.dao.example.SecurityAppEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityAppEntityMapper {
    long countByExample(SecurityAppEntityExample example);

    int insert(SecurityAppEntity record);

    int insertSelective(SecurityAppEntity record);

    List<SecurityAppEntity> selectByExample(SecurityAppEntityExample example);

    SecurityAppEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SecurityAppEntity record, @Param("example") SecurityAppEntityExample example);

    int updateByExample(@Param("record") SecurityAppEntity record, @Param("example") SecurityAppEntityExample example);

    int updateByPrimaryKeySelective(SecurityAppEntity record);

    int updateByPrimaryKey(SecurityAppEntity record);
}