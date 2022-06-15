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
@ApiModel("配置的根")
public class DataXConfigBean implements Serializable {

    private static final long serialVersionUID = 1533606417054124764L;
    @ApiModelProperty("作业配置")
    private Job job;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("作业")
    public static class Job implements Serializable {

        private static final long serialVersionUID = 7041980872630929737L;

        @ApiModelProperty("作业配置，主要是并发数")
        private Setting setting = new Setting();

        @ApiModelProperty("详细内容，多张表的数据迁移")
        private List<Content> content = new LinkedList<>();

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("作业全局配置")
    public static class Setting implements Serializable {

        private static final long serialVersionUID = -7741516911762302330L;

        @ApiModelProperty("速度配置")
        private Speed speed = new Speed();


    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("速度配置")
    public static class Speed implements Serializable {

        private static final long serialVersionUID = 8818117185150688211L;

        @ApiModelProperty("并发数")
        private int channel = 4;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("单个表的配置内容")
    public static class Content implements Serializable {

        private static final long serialVersionUID = 739734603246206748L;

        @ApiModelProperty("读取配置")
        private Reader reader;

        @ApiModelProperty("写配置")
        private Writer writer;

    }
}