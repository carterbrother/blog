package com.springbootpractice.demo.data.dict.param.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@ApiModel("部署响应参数")
@Data
@Builder
public class DeployRes implements Serializable {
    private static final long serialVersionUID = 22082225541716956L;

    @ApiModelProperty("应用名称")
    private String appName;

    @ApiModelProperty("应用镜像")
    private String imageName;

    @ApiModelProperty("环境名称")
    private String envName;

    @ApiModelProperty("查看日志URL")
    private String logUrl;


}
