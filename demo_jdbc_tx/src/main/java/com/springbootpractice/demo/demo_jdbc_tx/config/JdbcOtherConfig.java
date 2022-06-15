package com.springbootpractice.demo.demo_jdbc_tx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * 说明：不常用的jdbc对象
 * @author carter
 * 创建时间： 2020年02月14日 8:56 上午
 **/
@Configuration
public class JdbcOtherConfig {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOtherConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(){
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("user_login")
                .usingColumns("username","password","sex","note")
                ;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    }

}
