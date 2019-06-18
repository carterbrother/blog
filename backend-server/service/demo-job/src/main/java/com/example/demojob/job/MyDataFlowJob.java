package com.example.demojob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.springx.bootdubbo.job.autoconfig.AbstractDataFlowJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 一个简单的作业
 * @date 2019年06月18日 16:31
 * @Copyright (c) carterbrother
 */
@Slf4j
@Component
public class MyDataFlowJob extends AbstractDataFlowJob<Long> {


    /**
     * 执行作业
     *
     * @param context  作业上下文
     * @param dataList 数据列表
     * @return 执行的完成的数据量
     */
    @Override
    public int doJob(ShardingContext context, List<Long> dataList) {
        dataList.forEach(item-> log.info("正在执行DataFlow任务：{}" , item));
        return 0;
    }

    /**
     * 获取待处理数据.
     *
     * @param shardingContext 分片上下文
     * @return 待处理的数据集合
     */
    @Override
    public List fetchData(ShardingContext shardingContext) {
        return Arrays.asList(1,2,3);
    }
}
