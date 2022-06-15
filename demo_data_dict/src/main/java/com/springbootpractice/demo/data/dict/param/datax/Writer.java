package com.springbootpractice.demo.data.dict.param.datax;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel("写配置")
public class Writer implements Serializable {

    private static final long serialVersionUID = -8351852737722962279L;

    @ApiModelProperty("写执行器的名称")
    private String name;

    @ApiModelProperty("写执行器的参数配置")
    private WriterParameter parameter = new WriterParameter();


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("写执行器的参数")
    public static class WriterParameter implements Serializable {

        private static final long serialVersionUID = 8968765286899981691L;

        @ApiModelProperty("用户名")
        private String username;

        @ApiModelProperty("密码")
        private String password;

        @ApiModelProperty("是否清空数据")
        private String truncate="true";

        @ApiModelProperty("批量插入的配置")
        private String batchSize="256";


        @ApiModelProperty("行配置")
        private List<String> column = new LinkedList<>();

        @ApiModelProperty("前置执行删除表数据sql")
        private List<String> preSql=new LinkedList<>();

        @JsonIgnore
        @ApiModelProperty("前置执行sql")
        private List<String> preInitTableSql =new LinkedList<>();

        @ApiModelProperty("连接信息")
        private List<WriteConnection> connection = new LinkedList<>();


    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("连接配置")
    public static class WriteConnection implements Serializable {

        private static final long serialVersionUID = -3010216566546692760L;
        @ApiModelProperty("连接的jdbcUrl")
        private String jdbcUrl;

        @ApiModelProperty("表名")
        private List<String> table=new LinkedList<>();


    }
}