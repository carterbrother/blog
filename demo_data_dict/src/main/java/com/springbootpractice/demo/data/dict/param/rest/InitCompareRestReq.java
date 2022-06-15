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
@ApiModel("初始化比较页面数据请求参数")
public class InitCompareRestReq implements Serializable {


    private static final long serialVersionUID = -4189397633692712838L;

    @ApiModelProperty("mysql的数据库")
    private String mysqlDatabaseName;

    @ApiModelProperty("oracle的数据库")
    private String oracleDatabaseName;



}
