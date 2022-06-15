package com.springbootpractice.demo.demo_jdbc_tx.biz;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年02月14日 9:41 上午
 **/
@Service
public class NamedJdbcBiz {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public NamedJdbcBiz(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Map getById(Number id){
        Map<String, Number> paramMap = new HashMap<>(1);
        paramMap.put("id",id);
        final Map<String, Object> stringObjectMap = namedParameterJdbcTemplate.queryForMap("select * from user_login where id=:id", paramMap);
        return stringObjectMap;
    }

}
