package com.springbootpractice.demo.data.dict.param.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 说明：连接信息参数
 * @author carter
 * 创建时间： 2020年02月03日 4:34 下午
 **/
@Data
@ApiModel("连接信息参数")
public class ConnectionReqParam implements Serializable {
    private static final long serialVersionUID = 8435495761805759123L;

    @ApiModelProperty("连接数据库类型，仅支持mysql和oracle")
    private String dbType="mysql";

    @ApiModelProperty("连接url,格式：jdbc:mysql://localhost:3306")
    private String connectionUrl;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
