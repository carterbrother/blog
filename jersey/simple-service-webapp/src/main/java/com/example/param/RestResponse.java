package com.example.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 说明：通用响应参数
 * @author carter
 * 创建时间： 2019年12月11日 2:11 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("通用响应参数")
public class RestResponse implements Serializable {

    private static final long serialVersionUID = -3683812975809745287L;
    @ApiModelProperty(value = "状态码",notes = "参考HTTP状态码")
    private Integer code;

    @ApiModelProperty("错误信息")
    private String message;

    @ApiModelProperty("数据")
    private Object data;

}
