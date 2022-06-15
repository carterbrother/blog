package com.springbootpractice.demo.data.dict.param.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author carter
 * create_date  2020/5/14 18:27
 * description     建表等语句的执行结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("建表等语句的执行结果")
public class DDLExecuteBo implements Serializable {
    private static final long serialVersionUID = 842373091594889847L;

    @ApiModelProperty("sql语句")
    private String sql;

    @ApiModelProperty("错误信息")
    private String err;

    @ApiModelProperty("执行结果 0 成功 非0失败")
    private int res;

}
