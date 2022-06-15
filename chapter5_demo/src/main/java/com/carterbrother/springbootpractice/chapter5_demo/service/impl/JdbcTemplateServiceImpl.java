package com.carterbrother.springbootpractice.chapter5_demo.service.impl;

import com.carterbrother.springbootpractice.chapter5_demo.entity.User;
import com.carterbrother.springbootpractice.chapter5_demo.entity.enums.SexEnum;
import com.carterbrother.springbootpractice.chapter5_demo.service.JdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月20日 6:02 PM
 * @Copyright (c) carterbrother
 */
@Service
public class JdbcTemplateServiceImpl  implements JdbcTemplateService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public User getUser(Long id) {

        String sql = "select id,user_name,sex,note,deleted from t_user where id=? and deleted=0  ";
        return jdbcTemplate.queryForObject(sql,getUserMapper(),id);
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        String sql = " select id,user_name,sex,note,deleted from t_user where user_name like concat('%',?,'%') and note like concat('%',?,'%')  and deleted =0";
        return jdbcTemplate.query(sql,new Object[]{userName,note},getUserMapper());
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user(user_name,sex,note) values(?,?,?) ";
        return jdbcTemplate.update(sql,user.getUserName(),user.getSex().getId(),user.getNote());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set user_name=? , sex=? , note=? where id=?";
        return jdbcTemplate.update(sql,user.getUserName(),user.getSex().getId(),user.getNote(),user.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "update t_user set deleted=1  where id=? ";
        return jdbcTemplate.update(sql,id);
    }

    private RowMapper<User> getUserMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            user.setSex(SexEnum.getEnumById(rs.getInt("sex")));
            user.setNote(rs.getString("note"));
            user.setDeleted(rs.getBoolean("deleted"));
            return user;
        };
    }

}
