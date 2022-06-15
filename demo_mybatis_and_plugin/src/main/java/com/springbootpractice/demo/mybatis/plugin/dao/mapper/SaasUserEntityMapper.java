package com.springbootpractice.demo.mybatis.plugin.dao.mapper;

import com.springbootpractice.demo.mybatis.plugin.dao.entity.SaasUserEntity;
import com.springbootpractice.demo.mybatis.plugin.dao.example.SaasUserEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaasUserEntityMapper {
    long countByExample(SaasUserEntityExample example);

    int insert(SaasUserEntity record);

    int insertSelective(SaasUserEntity record);

    List<SaasUserEntity> selectByExample(SaasUserEntityExample example);

    SaasUserEntity selectByPrimaryKey(Integer id);

    SaasUserEntity selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") SaasUserEntity record, @Param("example") SaasUserEntityExample example);

    int updateByExample(@Param("record") SaasUserEntity record, @Param("example") SaasUserEntityExample example);

    int updateByPrimaryKeySelective(SaasUserEntity record);

    int updateByPrimaryKey(SaasUserEntity record);

    int logicalDeleteByExample(@Param("example") SaasUserEntityExample example);

    int logicalDeleteByPrimaryKey(Integer id);
}