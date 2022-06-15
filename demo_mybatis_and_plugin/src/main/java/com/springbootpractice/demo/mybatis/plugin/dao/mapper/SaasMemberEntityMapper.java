package com.springbootpractice.demo.mybatis.plugin.dao.mapper;

import com.springbootpractice.demo.mybatis.plugin.dao.entity.SaasMemberEntity;
import com.springbootpractice.demo.mybatis.plugin.dao.example.SaasMemberEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaasMemberEntityMapper {
    long countByExample(SaasMemberEntityExample example);

    int insert(SaasMemberEntity record);

    int insertSelective(SaasMemberEntity record);

    List<SaasMemberEntity> selectByExample(SaasMemberEntityExample example);

    SaasMemberEntity selectByPrimaryKey(Integer id);

    SaasMemberEntity selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") SaasMemberEntity record, @Param("example") SaasMemberEntityExample example);

    int updateByExample(@Param("record") SaasMemberEntity record, @Param("example") SaasMemberEntityExample example);

    int updateByPrimaryKeySelective(SaasMemberEntity record);

    int updateByPrimaryKey(SaasMemberEntity record);

    int logicalDeleteByExample(@Param("example") SaasMemberEntityExample example);

    int logicalDeleteByPrimaryKey(Integer id);
}