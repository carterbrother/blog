package com.springbootpractice.demo.demo_jdbc_tx.biz;

import lombok.NonNull;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年02月14日 9:00 上午
 **/
@Service
public class SimpleJdbcInsertBiz {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public SimpleJdbcInsertBiz(SimpleJdbcInsert simpleJdbcInsert) {
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    public long insertUserLogin(String username,String password,int sex,String note){

        Map<String, Object> argsMap = new HashMap<>(4);
        argsMap.put("username",username);
        argsMap.put("password",password);
        argsMap.put("sex",sex);
        argsMap.put("note",note);

        final Number id = simpleJdbcInsert
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(argsMap);

        return id.longValue();
    }

}
