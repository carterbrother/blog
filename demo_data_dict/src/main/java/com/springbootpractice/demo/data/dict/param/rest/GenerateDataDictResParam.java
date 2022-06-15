package com.springbootpractice.demo.data.dict.param.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 说明：生成的数据字典markdown响应参数
 * @author carter
 * 创建时间： 2020年02月03日 4:43 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("生成的数据字典markdown响应参数")
public class GenerateDataDictResParam {

    @ApiModelProperty("markdown内容")
    private String markdownContent;

}
