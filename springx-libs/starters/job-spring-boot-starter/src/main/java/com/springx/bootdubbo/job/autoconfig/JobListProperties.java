package com.springx.bootdubbo.job.autoconfig;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 作业的配置属性
 * @date 2019年06月13日 16:35
 * @Copyright (c) carterbrother
 */
@Component
@ConfigurationProperties(prefix = "job.config")
@Data
@ToString
public class JobListProperties {

    /**
     * 多任务配置，每个对应一个任务的配置
     */
    private List<SingleJobProperties> settings  = Lists.newLinkedList();
    /**
     * 应用空间名称,一般取服务名
     */
    private String appNameSpace;

    /**
     * zookeeper的服务器配置
     */
    private String zkServerLists;

    /**
     * 最大重试次数.
     */
    private Integer maxRetries = 3 ;

    /**
     * 等待重试的间隔时间的初始值.
     * 单位毫秒.
     */
    private  int baseSleepTimeMilliseconds = 1000;

    /**
     * 等待重试的间隔时间的最大值.
     * 单位毫秒.
     */
    private  int maxSleepTimeMilliseconds = 3000;



}
