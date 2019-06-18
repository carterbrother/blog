package com.springx.bootdubbo.job.autoconfig;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.google.common.base.Throwables;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.springx.bootdubbo.common.util.JsonUtil;
import com.springx.bootdubbo.common.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:fuchun.li@lifesense.com">fuchun.li</a>
 * @description 抽象的定时作业
 * @date 2019年01月16日 3:00 PM
 * @Copyright (c) 2018, lifesense.com
 */
@Slf4j
 abstract   class AbstractJob  implements DisposableBean {

    /**
     * 线程池
     */
    private ExecutorService singlePool ;
    /**
     * 单个作业的任务
     */
    public SingleJobProperties setting;


    public void setSetting(SingleJobProperties setting) {
        if (!Objects.equals(setting,this.setting)){
            this.setting = setting;
        }
    }


    public void executeJob(ShardingContext shardingContext, List list , DoMyJob myJobFunction) {
        Assert.isTrue(Objects.nonNull(setting), "setting must not null");
        int timeoutSecond = setting.getExecuteTimeOut();
        if (timeoutSecond < 1) {
            timeoutSecond = Ints.tryParse(System.getProperty("jobExecuteTimeOut", String.valueOf(12 * 60 * 60)));
        }

        final String jobName = setting.getJobName();
        if (Objects.isNull(singlePool)) {
            singlePool = new ThreadPoolExecutor(1,
                    Runtime.getRuntime().availableProcessors(),
                    timeoutSecond,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(100),
                    new ThreadFactoryBuilder().setNameFormat(jobName + "-job-pool-%d").build(),
                    new ThreadPoolExecutor.AbortPolicy());
        }

        SimpleTimeLimiter simpleTimeLimiter = new SimpleTimeLimiter(singlePool);
        final String appName = System.getProperty("serviceName", "unknown");
        StopWatch stopWatch = new StopWatch(UUIDUtil.uuid());
        stopWatch.start(appName.concat(":"+jobName));
        try {
            simpleTimeLimiter.callWithTimeout(() -> {
                myJobFunction.execute(shardingContext,list);
                return jobName;
            }, timeoutSecond, TimeUnit.SECONDS,false);

        } catch (Exception ex) {
            log.error("lx_elastic_job_execute_trace:{}:{}:{}:\n{}:\n{}", stopWatch.getId(), appName, jobName, JsonUtil.toJson(setting), Throwables.getStackTraceAsString(ex));
        } finally {
            stopWatch.stop();
            log.info("lx_elastic_job_execute_trace:{}:{}:{}:\n{}:\n{}", stopWatch.getId(), appName, jobName,JsonUtil.toJson(setting), stopWatch.prettyPrint());
        }
    }

    @Override
    public void destroy() throws Exception {
        if (Objects.nonNull(singlePool)){
            singlePool.shutdown();
        }
    }
}