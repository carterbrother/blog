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
 * create_date  2020/5/9 23:27
 * description     索引的部分元数据
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("索引的部分元数据")
public class IndexBo implements Serializable {

    private static final long serialVersionUID = 7134073475083387892L;

    @ApiModelProperty("索引的字段名称")
    private String columnName;

    @ApiModelProperty("字段顺序")
    private Integer seqInIndex;

    @ApiModelProperty("索引名称")
    private String IndexName;

    @ApiModelProperty("表名")
    private String tableName;
}
