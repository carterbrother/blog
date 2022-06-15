package com.springbootpractice.demo.redis.dao.mapper;

import java.util.List;

import com.springbootpractice.demo.redis.dao.entity.UserLoginExtEntity;
import com.springbootpractice.demo.redis.dao.example.UserLoginExtEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginExtEntityMapper {
    long countByExample(UserLoginExtEntityExample example);

    int insert(UserLoginExtEntity record);

    int insertSelective(UserLoginExtEntity record);

    List<UserLoginExtEntity> selectByExample(UserLoginExtEntityExample example);

    UserLoginExtEntity selectByPrimaryKey(Integer id);

    UserLoginExtEntity selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    int updateByExampleSelective(@Param("record") UserLoginExtEntity record, @Param("example") UserLoginExtEntityExample example);

    int updateByExample(@Param("record") UserLoginExtEntity record, @Param("example") UserLoginExtEntityExample example);

    int updateByPrimaryKeySelective(UserLoginExtEntity record);

    int updateByPrimaryKey(UserLoginExtEntity record);

    int logicalDeleteByExample(@Param("example") UserLoginExtEntityExample example);

    int logicalDeleteByPrimaryKey(Integer id);
}
