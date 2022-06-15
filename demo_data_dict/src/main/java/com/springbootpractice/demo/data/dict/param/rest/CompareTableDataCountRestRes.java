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
 * create_date  2020/5/9 10:26
 * description    表的数据数量比对结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("表的数据数量比对结果")
public class CompareTableDataCountRestRes implements Serializable {
    private static final long serialVersionUID = -6500261235292953660L;

    @ApiModelProperty("mysql表名")
    private String mysqlTableName;

    @ApiModelProperty("oracle表名")
    private String oracleTableName;

    @ApiModelProperty("mysql的表对应的数据总数")
    private Long mysqlTableDataCount;

    @ApiModelProperty("oracle的表对应的数据总数")
    private Long oracleTableDataCount;

    @ApiModelProperty("对比结果")
    private String  msg;


}
