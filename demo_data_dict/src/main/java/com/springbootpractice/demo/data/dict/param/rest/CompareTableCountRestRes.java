package com.springbootpractice.demo.data.dict.param.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author     carter
 * 创建日期:  2020/5/8 18:05
 * 描述:     表的数量比对结果
 * @author lifuchun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("表的数量比对结果")
public class CompareTableCountRestRes implements Serializable {


    private static final long serialVersionUID = -8046527528045886907L;

    @ApiModelProperty("mysql的数量")
    private Long mysqlTableCount;

    @ApiModelProperty("oracle的表数量")
    private Long oracleTableCount;

    @ApiModelProperty("表数量对比结果")
    private String msg;

    @ApiModelProperty("如果数量对不上，mysql独有的表")
    private List<String> mysqlOwnTable;

    @ApiModelProperty("如果数量对不上，oracle独有的表")
    private List<String> oracleOwnTable;
}
