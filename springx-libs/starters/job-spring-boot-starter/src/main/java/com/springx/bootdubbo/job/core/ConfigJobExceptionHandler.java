package com.springx.bootdubbo.job.core;

import com.dangdang.ddframe.job.exception.JobSystemException;
import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:fuchun.li@lifesense.com">fuchun.li</a>
 * @description 当定时任务出现异常的时候，进行日志处理，运维监控日志进行邮件报警
 * @date 2019年01月15日 10:12 AM
 * @Copyright (c) 2018, lifesense.com
 */
public class ConfigJobExceptionHandler implements JobExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(ConfigJobExceptionHandler.class);
    /**
     * 处理作业异常.
     *
     * @param jobName 作业名称
     * @param cause 异常原因
     */
    @Override
    public void handleException(String jobName, Throwable cause) {
        final String appName = System.getProperty("serviceName","unknown");
        final String stackTraceAsString = Throwables.getStackTraceAsString(cause);
        final String jobStatus = "exception";
        //lx_elastic_job:微服务名:作业名:执行结果状态:堆栈信息
        if (cause instanceof JobSystemException){
            logger.error("lx_elastic_job_execute_trace:{}:{}:{}:作业调度异常:\n{}", appName, jobName,jobStatus, stackTraceAsString);
        }else{
            logger.error("lx_elastic_job:{}:{}:{}:\n{}",appName,jobName, jobStatus, stackTraceAsString);
        }

    }
}