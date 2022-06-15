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
 * create_date  2020/5/9 15:31
 * description     datax配置生成必须的参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("datax配置生成必须的参数")
public class DataXRestReqVo implements Serializable {

    private static final long serialVersionUID = -2316792795976694295L;

    @ApiModelProperty("连接url,格式：jdbc:mysql://localhost:3306")
    private String mysqlConnectionUrl;

    @ApiModelProperty("用户名")
    private String mysqlUsername;

    @ApiModelProperty("密码")
    private String mysqlPassword;

    @ApiModelProperty("mysql的数据库名称")
    private String mysqlDatabaseName;

    @ApiModelProperty("oracle连接url")
    private String oracleConnectionUrl;

    @ApiModelProperty("oracle用户名")
    private String oracleUsername;

    @ApiModelProperty("oracle密码")
    private String oraclePassword;

    @ApiModelProperty("oracle的数据库名称")
    private String oracleDatabaseName;

}
