package com.springx.bootdubbo.job.core;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.JobEventListener;

import java.io.Serializable;

/**
 * @author <a href="mailto:fuchun.li@lifesense.com">fuchun.li</a>
 * @description 事件配置
 * @date 2019年01月15日 11:22 AM
 * @Copyright (c) 2018, lifesense.com
 */
public class ConfigJobEventConfiguration implements JobEventConfiguration, Serializable {

    public static final String LX_JOB_EVENT_IDENTITY = "lx_log";

    /**
     * 创建作业事件监听器.
     *
     * @return 作业事件监听器.
     */
    @Override
    public JobEventListener createJobEventListener() {
        return new ConfigJobEventListener();
    }

    /**
     * 获取作业事件标识.
     *
     * @return 作业事件标识
     */
    @Override
    public String getIdentity() {
        return LX_JOB_EVENT_IDENTITY;
    }
}