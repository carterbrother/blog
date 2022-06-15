package com.carterbrother.springbootpractice.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 用户服务
 * @date 2019年04月10日 2:28 PM
 * @Copyright (c) carterbrother
 */
@Component
public class UserDetailBean implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        String password =  jdbcTemplate.queryForObject("select psw from t_user where user_name=? "
                , new  Object[]{username}
                , (resultSet, i) -> resultSet.getNString(1));


        List<GrantedAuthority> authorities = jdbcTemplate.query("select r.role_name from t_user u , t_user_role ru, t_role r  where u.id=ru.user_id and r.id=ru.role_id  and u.user_name = ? "
                , new Object[]{username},
                (resultSet, i) -> new SimpleGrantedAuthority(resultSet.getString(1)));


        UserDetails userDetails = new User(username,password,authorities);

        return userDetails;
    }
}
