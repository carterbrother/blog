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
 * 创建日期:  2020/5/9 10:09
 * 描述:     比对参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("比对参数")
public class CompareRestReq implements Serializable {
    private static final long serialVersionUID = -4236918009970606281L;

    @ApiModelProperty("mysql的数据库名")
    private String mysqlDatabase;

    @ApiModelProperty("mysql的表名")
    private String mysqlTableName;

    @ApiModelProperty("oracle的数据库名")
    private String oracleDatabase;

    @ApiModelProperty("oracle的表名")
    private String oracleTableName;


}
