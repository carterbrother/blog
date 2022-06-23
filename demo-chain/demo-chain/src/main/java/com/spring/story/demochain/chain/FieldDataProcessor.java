package com.spring.story.demochain.chain;

import com.baomidou.mybatisplus.core.metadata.IPage;

public interface FieldDataProcessor<T> {

    /**
     * 是否匹配, 是否需要执行
     * 
     * @param t T 数据，含类型
     * @return boolean
     */
    boolean match(T t);

    /**
     * 处理类级别.
     *
     * @return Level
     */
    int processLevel();

    /**
     * 实现方法\
     * 
     * @param query    当前查询的条件对象
     * @param pageInfo 当前查询返回的结果集
     */
    void queryResultProcess(Query query, IPage<WorksheetData> pageInfo);

    /**
     * 判断是否是该类处理的级别
     * 
     * @param level int
     * @return boolean
     */
    default boolean matchLevel(int level) {
        return (level & processLevel()) != 0;
    }

    /**
     * 是否匹配, 是否需要执行
     * 
     * @param t     Query
     * @param level int
     * @return boolean
     */
    default boolean match(T t, int level) {
        return match(t) && matchLevel(level);
    }

}
