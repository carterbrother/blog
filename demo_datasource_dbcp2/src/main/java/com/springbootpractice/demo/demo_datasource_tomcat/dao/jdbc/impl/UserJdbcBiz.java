package com.springbootpractice.demo.demo_datasource_tomcat.dao.jdbc.impl;

import com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.UserLoginEntity;
import com.springbootpractice.demo.demo_datasource_tomcat.dao.entity.enums.SexEnum;
import com.springbootpractice.demo.demo_datasource_tomcat.dao.jdbc.IUserJdbcBiz;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 2:36 下午
 **/
@Service
public class UserJdbcBiz implements IUserJdbcBiz {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcBiz(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserLoginEntity getUserLogin(Long id) {

        String sql = "SELECT id,user_name,password,sex,note FROM user_login WHERE id=? ";
        Object[] params = {id};
        return jdbcTemplate.queryForObject(sql, params, getUserLoginMapper());
    }


    @Override
    public List<UserLoginEntity> findUserLogin(String userName, String note) {

        String sql = "SELECT id,user_name,password,sex,note FROM user_login WHERE user_name=? and note=?";
        Object[] params = {userName, note};
        return Optional.ofNullable(jdbcTemplate.query(sql, params, getUserLoginMapper()))
                .orElse(Collections.emptyList());
    }

    @Override
    public long createUserLogin(UserLoginEntity entity) {
        String sql = "INSERT INTO user_login(user_name, password, sex, note) VALUES (?,?,?,?)";
        Object[] params = {entity.getUserName(), entity.getPassword(), entity.getSex().getCode(), entity.getNote()};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public long updateUserLogin(UserLoginEntity entity) {
        String sql = "UPDATE user_login SET user_name=? , password=? , sex=? , note=? WHERE id=? ";
        Object[] params = {entity.getUserName(), entity.getPassword(), entity.getSex().getCode(), entity.getNote(), entity.getId()};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public long deleteUserLogin(Long id) {
        String sql = "DELETE FROM user_login WHERE id=? ";
        Object[] params = {id};
        return jdbcTemplate.update(sql, params);
    }

    public static RowMapper<UserLoginEntity> getUserLoginMapper() {
        return (ResultSet rs, int rowNum) -> UserLoginEntity.builder()
                .id(rs.getLong("id"))
                .userName(rs.getString("user_name"))
                .password(rs.getString("password"))
                .sex(SexEnum.getByCode(rs.getInt("sex")))
                .note(rs.getString("note"))
                .build();

    }


}
