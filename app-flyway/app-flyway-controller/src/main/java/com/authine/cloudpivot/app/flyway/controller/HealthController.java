package com.authine.cloudpivot.app.flyway.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carter
 * create_date  2020/6/4 19:02
 * description     健康检查接口
 */
@Api(tags = "健康检查")
@RestController
public class HealthController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private FlywayProperties flywayProperties;

    
    @ApiOperation("回声检测")
    @GetMapping("/echo")
    public Response echo(){
        return SingleResponse.of(applicationName);
    }

    @ApiOperation("flyway配置查看")
    @GetMapping("/flyway/config")
    public Response flywayConfig(){
        return SingleResponse.of(flywayProperties);
    }


}
