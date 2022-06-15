package com.springbootpractice.demo.mybatis.plugin.dao.mapper;

import com.springbootpractice.demo.mybatis.plugin.dao.entity.MechantConfigEntity;
import com.springbootpractice.demo.mybatis.plugin.dao.example.MechantConfigEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MechantConfigEntityMapper {
    long countByExample(MechantConfigEntityExample example);

    int insert(MechantConfigEntity record);

    int insertSelective(MechantConfigEntity record);

    List<MechantConfigEntity> selectByExample(MechantConfigEntityExample example);

    MechantConfigEntity selectByPrimaryKey(Integer id);

    MechantConfigEntity selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") MechantConfigEntity record, @Param("example") MechantConfigEntityExample example);

    int updateByExample(@Param("record") MechantConfigEntity record, @Param("example") MechantConfigEntityExample example);

    int updateByPrimaryKeySelective(MechantConfigEntity record);

    int updateByPrimaryKey(MechantConfigEntity record);

    int logicalDeleteByExample(@Param("example") MechantConfigEntityExample example);

    int logicalDeleteByPrimaryKey(Integer id);
}