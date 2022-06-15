package com.springbootpractice.demo.data.dict.param.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 说明：rest接口统一返回实体
 * @author carter
 * 创建时间： 2020年02月03日 4:22 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("rest接口统一返回实体")
public class  RestResponseBean <T extends Object> implements Serializable {
    private static final long serialVersionUID = -6229606498548401773L;

    public static RestResponseBean SUCCESS_BEAN =RestResponseBean.builder().code(HttpStatus.OK.value()).msg(HttpStatus.OK.name()).build();

    @ApiModelProperty("响应码，对应http的状态码")
    private Integer code;

    @ApiModelProperty("错误信息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;


}
