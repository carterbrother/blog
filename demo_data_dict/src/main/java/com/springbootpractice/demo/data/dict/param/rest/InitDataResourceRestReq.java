package com.springbootpractice.demo.data.dict.param.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author carter
 * create_date  2020/5/13 10:34
 * description     数据源
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("数据源")
public class InitDataResourceRestReq implements Serializable {

    private static final long serialVersionUID = 4693709176426409104L;

    @ApiModelProperty("连接url,格式：jdbc:mysql://localhost:3306")
    private String connectionUrl;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("数据库类型，只支持mysql,oracle")
    private String databaseType;

}
