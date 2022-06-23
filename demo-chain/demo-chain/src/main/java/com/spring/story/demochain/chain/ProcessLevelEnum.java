package com.spring.story.demochain.chain;

import lombok.Getter;

public enum ProcessLevelEnum {

    BASE(1, "所有的查询都会执行"),
    EXTEND(1 << 1, "需要关联数据时才会执行"),
    CHOICE_CONVERT(1 << 2, " 改执行结果依赖 extend 执行后的结果。表示要需要将选择控件的 key 值转换为对应的 value 值。");

    @Getter
    private Integer intVal;

    @Getter
    private String title;

    private ProcessLevelEnum(Integer intVal, String title) {
        this.intVal = intVal;
        this.title = title;
    }

}