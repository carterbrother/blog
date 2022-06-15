package com.springbootpractice.demo.tx.transactional.dao.mapper;

import com.springbootpractice.demo.tx.transactional.dao.entity.UserLoginEntity;
import com.springbootpractice.demo.tx.transactional.dao.example.UserLoginEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginEntityMapper {
    long countByExample(UserLoginEntityExample example);

    int insert(UserLoginEntity record);

    int insertSelective(UserLoginEntity record);

    List<UserLoginEntity> selectByExample(UserLoginEntityExample example);

    UserLoginEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLoginEntity record, @Param("example") UserLoginEntityExample example);

    int updateByExample(@Param("record") UserLoginEntity record, @Param("example") UserLoginEntityExample example);

    int updateByPrimaryKeySelective(UserLoginEntity record);

    int updateByPrimaryKey(UserLoginEntity record);
}