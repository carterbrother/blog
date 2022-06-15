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
 * 创建日期:  2020/5/8 18:39
 * 描述:     表的定义比对结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("表的定义比对结果")
public class CompareTableFieldTypeRestRes implements Serializable {
    private static final long serialVersionUID = 3834535175193231928L;

    @ApiModelProperty("mysql字段名称")
    private List<TableDefinitionBo> tableDefinitionBoList;

    @ApiModelProperty("mysql索引列表")
    private List<String> mIndexList;

    @ApiModelProperty("oracle索引列表")
    private List<String> oIndexList;

    @ApiModelProperty("对比结果")
    private String msg;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("对比字段定义")
    public static class TableDefinitionBo{

        @ApiModelProperty("mysql的字段类型")
        private String mysqlFieldType;

        @ApiModelProperty("oracle的字段类型")
        private String oracleFieldType;

    }

}
