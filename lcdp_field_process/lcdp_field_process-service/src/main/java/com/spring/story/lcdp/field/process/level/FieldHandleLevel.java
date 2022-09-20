package com.spring.story.lcdp.field.process.level;

import java.util.Arrays;

public final class FieldHandleLevel {




    public static void main(String[] args) {
        /**
         * base 级别表示所有的查询都会执行
         */
        int BASE = 1;

        /**
         * extend 表示只有需要关联数据时才会执行, 如 queryInfo
         */
        int EXTEND = 1 << 1;

        /**
         * 改执行结果依赖 extend 执行后的结果。
         * 表示要需要将选择控件的 key 值转换为对应的 value 值。
         */
        int CHOICE_CONVERT = 1 << 2;

        /**
         * 关联控件关联结果
         */
        int RELATION = 1 << 3;

        /**
         * 获取关联控件的标题信息
         */
        int RELATION_TITLE = 1 << 4;

        /**
         * 关联数据查询，标题外置
         */
        int RELATION_QUERY_ADD_TITLE = 1 << 5;

        /**
         * 扩展家关联控件
         */
        int EXTEND_RELATION = EXTEND + RELATION;


        Arrays.asList(BASE,EXTEND,CHOICE_CONVERT,RELATION, RELATION_TITLE,RELATION_QUERY_ADD_TITLE,EXTEND_RELATION).forEach(System.out::println);


    }

}
