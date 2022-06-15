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
 * create_date  2020/5/11 15:30
 * description     生成oracle的初始化脚本
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("生成oracle的初始化脚本")
public class OracleSqlRestRes implements Serializable {
    private static final long serialVersionUID = -1813800495221295243L;

    @ApiModelProperty("oracle的建表脚本")
    private String oracleSql;

    @ApiModelProperty("生成的sql脚本的全路径")
    private String filePathName;
}
