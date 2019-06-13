package com.springx.bootdubbo.job.autoconfig;

import com.springx.bootdubbo.job.core.JobConstant;
import lombok.Data;
import org.quartz.JobListener;

import java.util.List;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description  定时任务暴露出来的配置
 * @date 2019年06月13日 16:15
 * @Copyright (c) carterbrother
 */
@Data
public class SingleJobProperties {

    /**
     * 作业中文描述信息
     */
    private String jobDescription="";
    /**
     * 作业名称
     */
    private String jobName;

    /**
     * 任务类型:
     * 	simple:简单任务
     *  dataflow:流式任务
     */
    private String jobType= JobConstant.TYPE_SIMPLE;

    /**
     * 作业实现类
     */
    private Class jobClass;

    /**
     * 作业分片总数
     */
    private int shardingTotalCount=1;

    /**
     * 作业启动时间的cron表达式
     */
    private String cron;

    /**
     * 分片序列号和个性化参数对照表.
     *
     * <p>
     * 分片序列号和参数用等号分隔, 多个键值对用逗号分隔. 类似map.
     * 分片序列号从0开始, 不可大于或等于作业分片总数.
     * 如:
     * 0=a,1=b,2=c
     * </p>
     *
     */
    private String shardingItemParameters="0=a";

    /**
     * 作业自定义参数.
     *
     * <p>
     * 可以配置多个相同的作业, 但是用不同的参数作为不同的调度实例.
     * </p>
     *
     */
    private String jobParameter="1";

    /**
     * 是否开启失效转移
     */
    private boolean failover=true;

    /**
     * 是否开启错过任务重新执行
     */
    private boolean misfire=true;

    /**
     * 处理线程数
     * 只对dataflow类型的job有效
     */
    private int processThreadCount=1;

    /**
     * 抓取数据总数
     */
    private int fetchDataCount=1;

    /**
     * 整个任务一次监听器
     */
    private List<JobListener> onceListeners;

    /**
     * 每个节点监听器
     */
    private List<JobListener> nodeListeners;

    /**
     * 任务执行超时时间，单位秒
     */
    private int executeTimeOut;

}
