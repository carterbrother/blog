package com.springbootpractice.demo.data.dict.param.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@ApiModel("部署参数")
@Data
@Builder
public class DeployReq implements Serializable {
    private static final long serialVersionUID = 22082225541716956L;

    @ApiModelProperty("应用名称")
    private String appName;

    @ApiModelProperty("应用镜像")
    private String imageName;

    @ApiModelProperty("环境名称")
    private String envName;


}
