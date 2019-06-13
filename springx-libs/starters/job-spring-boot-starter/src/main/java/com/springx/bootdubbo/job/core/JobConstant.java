package com.springx.bootdubbo.job.core;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 作业组件中涉及到的常量
 * @date 2019年06月13日 16:21
 * @Copyright (c) carterbrother
 */
public class JobConstant {

    private JobConstant(){}

    /**
     * 简单任务
     */
    public static final String TYPE_SIMPLE="simple";
    /**
     * 流式任务
     */
    public static final String TYPE_DATA_FLOW ="dataflow";
    /**
     * 命名空间
     */
    public static final String NAMESPACE="_job/";
    /**
     * job异常处理的配置key
     */
    public static final String JOB_EXCEPTION_HANDLER_KEY = "job_exception_handler";
    /**
     * schedule的后缀
     */
    public static final String SCHEDULE_SUB_FIX ="_schedule";

    /**
     * 作业机与zookeeper的最大时间误差(秒)
     */
    public static final int MAX_TIME_DIFF_SECOND=60;



}
