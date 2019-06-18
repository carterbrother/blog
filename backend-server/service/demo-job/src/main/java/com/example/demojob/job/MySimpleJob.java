package com.example.demojob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.springx.bootdubbo.job.autoconfig.AbstractSimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 一个简单的作业
 * @date 2019年06月18日 16:31
 * @Copyright (c) carterbrother
 */
@Slf4j
@Component
public class MySimpleJob extends AbstractSimpleJob {

    /**
     * 执行作业
     *
     * @param context 作业上下文
     * @return
     */
    @Override
    public void doJob(ShardingContext context) {

        log.info("===> 执行简单任务 ====" );

    }


}
