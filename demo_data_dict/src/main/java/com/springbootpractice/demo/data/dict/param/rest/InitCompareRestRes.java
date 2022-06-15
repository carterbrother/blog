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
 * create_date  2020/5/13 11:09
 * description     TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("初始比较页面响应参数")
public class InitCompareRestRes implements Serializable {

    private static final long serialVersionUID = -8435611946795080359L;

    @ApiModelProperty("数量消息，格式，mysql的数据库a表数量x个，oracle的数据库b表数量y个")
    private String countMsg;

    private List<TableCompareBo> tableCompareBos = new LinkedList<>();

    @ApiModelProperty("总的校验信息")
    private String totalMsg;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("表格内容")
    public static class TableCompareBo implements Serializable {
        private static final long serialVersionUID = -5700668794718621355L;

        @ApiModelProperty("mysql的表名")
        private String mysqlTableName;

        @ApiModelProperty("oracle的表名")
        private String oracleTableName;

    }
}
