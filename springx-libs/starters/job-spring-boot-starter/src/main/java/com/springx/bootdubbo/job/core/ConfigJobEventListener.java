package com.springx.bootdubbo.job.core;

import com.dangdang.ddframe.job.event.JobEventListener;
import com.dangdang.ddframe.job.event.type.JobExecutionEvent;
import com.dangdang.ddframe.job.event.type.JobStatusTraceEvent;
import com.springx.bootdubbo.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author <a href="mailto:fuchun.li@lifesense.com">fuchun.li</a>
 * @description 作业事件监听器,目前只写日志，后续考虑改造成kafka入库
 * @date 2019年01月15日 11:06 AM
 * @Copyright (c) 2018, lifesense.com
 */
public class ConfigJobEventListener implements JobEventListener {


    private static final Logger logger = LoggerFactory.getLogger(ConfigJobEventListener.class);

    /**
     * 作业执行事件监听执行.
     *
     * @param jobExecutionEvent 作业执行事件
     */
    @Override
    public void listen(JobExecutionEvent jobExecutionEvent) {
        String jobStatus = "";
        if (null == jobExecutionEvent.getCompleteTime()) {
             jobStatus="init";
        } else {
            if (jobExecutionEvent.isSuccess()) {
                jobStatus="success";
            } else {
                jobStatus="error";
            }
        }
        saveJobExecutionEventLog(jobExecutionEvent,jobStatus);
    }

    private void saveJobExecutionEventLog(JobExecutionEvent jobExecutionEvent,String jobStatus) {
        if (Objects.equals(jobStatus, "error")) {
            logger.error("lx_elastic_job_execute_trace:{}:{}:{}:{}", getAppName(), jobExecutionEvent.getJobName(), jobStatus, JsonUtil.toJson(jobExecutionEvent));
        } else {
            //lx_elastic_job_execute_trace:appName:jobName:jobStatus:nodeInfo:eventJson:
            logger.info("lx_elastic_job_execute_trace:{}:{}:{}:{}", getAppName(), jobExecutionEvent.getJobName(), jobStatus, JsonUtil.toJson(jobExecutionEvent));
        }
    }

    /**
     * 作业状态痕迹事件监听执行.
     *
     * @param jobStatusTraceEvent 作业状态痕迹事件
     */
    @Override
    public void listen(JobStatusTraceEvent jobStatusTraceEvent) {
        //lx_elastic_job_status_trace:appName:jobName:nodeInfo:eventJson
//        logger.info("lx_elastic_job_status_trace:{}:{}:{}", getAppName(), jobStatusTraceEvent.getJobName(),JsonUtils.toJson(jobStatusTraceEvent));
    }

    /**
     * 获取作业事件标识.
     *
     * @return 作业事件标识
     */
    @Override
    public String getIdentity() {
        return ConfigJobEventConfiguration.LX_JOB_EVENT_IDENTITY;
    }

    /**
     * 得到应用的名称
     * @return
     */
    private String getAppName(){
        final String restName = System.getProperty("serviceName","unknown");
        return restName;
    }
}