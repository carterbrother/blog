package com.springbootpractice.egproduct.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 新增actuator的一个端点
 * @date 2019年06月21日 18:42
 * @Copyright (c) carterbrother
 */
@Endpoint(id = "dbCheck",enableByDefault = true)
@Component
public class DBCheckEndpoint {

    @ReadOperation
    public String test(){
        return "db check ok";
    }


}
