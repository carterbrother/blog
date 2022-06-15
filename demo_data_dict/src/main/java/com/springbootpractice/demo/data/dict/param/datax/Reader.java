package com.springbootpractice.demo.data.dict.param.datax;

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
 * @author lifuchun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("读取数据配置")
public class Reader implements Serializable {

    private static final long serialVersionUID = 6939984117604922438L;
    @ApiModelProperty("读取配置的名称")
    private String name;

    @ApiModelProperty("读取的参数")
    private ReaderParameter parameter;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("读执行器的参数配置")
    public static class ReaderParameter implements Serializable{

        @ApiModelProperty("用户名")
        private String username;

        @ApiModelProperty("密码")
        private String password;

        @ApiModelProperty("连接信息")
        private List<ReaderConnection> connection = new LinkedList<>();

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("读执行器的连接配置")
    public static class ReaderConnection implements Serializable{

        private static final long serialVersionUID = 1583586812607825512L;

        @ApiModelProperty("查询sql")
        private List<String> querySql= new LinkedList<>();

        @ApiModelProperty("连接的jdbcUrl")
        private List<String> jdbcUrl = new LinkedList<>();



    }

}