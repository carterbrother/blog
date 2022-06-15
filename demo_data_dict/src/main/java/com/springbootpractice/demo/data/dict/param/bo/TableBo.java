package com.springbootpractice.demo.data.dict.param.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 说明：表说明
 * @author carter
 * 创建时间： 2020年02月03日 10:15 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("表说明")
public class TableBo implements Serializable {
    private static final long serialVersionUID = -7365186679766065785L;

    @ApiModelProperty("表名，oracle,mysql都有")
    private String TABLE_NAME;

    @ApiModelProperty("表备注，mysql独有")
    private String TABLE_COMMENT;

    @ApiModelProperty("存储引擎，mysql独有")
    private String ENGINE;

    @ApiModelProperty("表的最后更新时间，oracle独有")
    private String LAST_ANALYZED;
}
