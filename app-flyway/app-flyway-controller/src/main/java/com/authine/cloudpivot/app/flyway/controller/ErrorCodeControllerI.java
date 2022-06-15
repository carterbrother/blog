package com.authine.cloudpivot.app.flyway.controller;

import com.alibaba.cola.exception.Exceptions;
import  com.authine.cloudpivot.app.flyway.domain.exception.DemoErrorCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carter
 * create_date  2020/7/8 11:19
 * description     错误测试接口
 */
@Api(tags = "常见错误演示")
@RestController
public class ErrorCodeControllerI {

    @ApiOperation( "校验异常")
    @GetMapping("/error/check_param")
    public void checkParamException() {
        Assert.isTrue(1 == 2, "xxx参数校验错误");
    }


    @ApiOperation("业务异常")
    @GetMapping("/error/biz")
    public void bizException() {
        Exceptions.throwBizException(DemoErrorCodeEnum.BIZ_CREATE_CONFIG_ERR);
    }

    @ApiOperation("系统异常")
    @GetMapping("/error/sys")
    public void sysException() {
        String a = null;
        a.getBytes();
    }

   

}
