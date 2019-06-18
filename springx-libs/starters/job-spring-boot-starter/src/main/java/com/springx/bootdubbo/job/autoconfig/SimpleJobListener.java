package com.springx.bootdubbo.job.autoconfig;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.springx.bootdubbo.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 作业监听器,可以参考这个模板对作业进行定制
 * @date 2019年06月13日 18:49
 * @Copyright (c) carterbrother
 */
@Slf4j
 class SimpleJobListener implements ElasticJobListener {
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {

        log.info("beforeJobExecuted===》{}", JsonUtil.toJson(shardingContexts));

    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("afterJobExecuted===》{}", JsonUtil.toJson(shardingContexts));

    }
}
