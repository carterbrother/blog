package com.springbootpractice.demo.data.dict.param.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author carter
 * create_date  2020/5/9 10:45
 * description     对比二进制字段结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("对比首行和尾行数据结果")
public class CompareTableRowDataRestRes implements Serializable {

    private static final long serialVersionUID = 4736565695816129566L;

    @ApiModelProperty("mysql表名")
    private String mysqlTableName;

    @ApiModelProperty("oracle表名")
    private String oracleTableName;

    @ApiModelProperty("对比结果")
    private String msg;

    @ApiModelProperty("首行")
    private List<CellBo> firstRow = new LinkedList<>();

    @ApiModelProperty("尾行")
    private List<CellBo> lastRow = new LinkedList<>();


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("单元格数据")
    public static class CellBo implements Serializable{

        private static final long serialVersionUID = 2295862809941383138L;

        @ApiModelProperty("mysql字段名")
        private String mKey;

        @ApiModelProperty("mysql字段值")
        private Object mValue;


        @ApiModelProperty("oracle字段名")
        private String oKey;

        @ApiModelProperty("oracle字段值")
        private Object oValue;

    }

}
