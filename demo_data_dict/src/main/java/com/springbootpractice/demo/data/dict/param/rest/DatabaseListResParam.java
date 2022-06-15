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
 * 说明：数据库列表
 * @author carter
 * 创建时间： 2020年02月03日 4:38 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("数据库列表")
public class DatabaseListResParam implements Serializable {
    private static final long serialVersionUID = 81932770236090322L;

    @ApiModelProperty("数据库名列表")
    private List<String> databaseList;

    @ApiModelProperty("oracle的数据源连接结果")
    private String oracleLinkMsg;
}
