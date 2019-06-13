package com.springx.bootdubbo.job.core;

import com.dangdang.ddframe.job.api.ShardingContext;

import java.util.List;

/**
 * @author <a href="mailto:fuchun.li@lifesense.com">fuchun.li</a>
 * @description 定义执行任务的函数式接口
 * @date 2019年01月16日 3:16 PM
 * @Copyright (c) 2018, lifesense.com
 */
@FunctionalInterface
public interface DoMyJob {
    /**
     * 传入分片，数据，返回执行结果
     * @param jobContext 分片上下文
     * @param list 分批数据
     * @return
     */
    int execute(ShardingContext jobContext, List list);
}