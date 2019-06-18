package com.springx.bootdubbo.job.autoconfig;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.google.common.base.Strings;
import com.springx.bootdubbo.common.util.GrayUtil;
import com.springx.bootdubbo.common.util.JsonUtil;
import com.springx.bootdubbo.common.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.springx.bootdubbo.job.autoconfig.JobConstant.*;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 定时作业的自动配置
 * @date 2019年06月13日 16:39
 * @Copyright (c) carterbrother
 */
@Configuration
@EnableConfigurationProperties(JobListProperties.class)
@Slf4j
@ConditionalOnProperty(prefix = "job.config", value = "enabled", havingValue = "true")
public class JobAutoConfig implements ApplicationContextAware {


    private ApplicationContext applicationContext;
    @Resource
    private JobListProperties jobListProperties;

    /**
     * 自动装配注册中心，即zookeeper
     *
     * @return
     */
    @Bean(destroyMethod = "close")
//    @ConditionalOnMissingBean(CoordinatorRegistryCenter.class)
    public CoordinatorRegistryCenter coordinatorRegistryCenter() {
        if (StringUtils.isNoneEmpty(GrayUtil.getGrayEnvVar())) {
            log.warn("current env[{}] is gray , don't start job!", GrayUtil.getGrayEnvVar());
            return null;
        }
        //非后台服务，不启动
        if (!SystemUtil.shouldStartupBackend()) {
            log.warn("current env[{}] is not backend , don't start job!", SystemUtil.getHostname());
            return null;
        }
        checkJobListProperties(jobListProperties);

        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(jobListProperties.getZkServerLists(), JobConstant.NAMESPACE);
        zookeeperConfiguration.setMaxRetries(jobListProperties.getMaxRetries());
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(jobListProperties.getBaseSleepTimeMilliseconds());
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(jobListProperties.getBaseSleepTimeMilliseconds());
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        regCenter.init();

        log.info("===job的zookeeper注册中心启动成功");


        final DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

        jobListProperties.getSettings().forEach(setting -> {
            Object jobBean = registerSingleJobToSpringContainer(setting, jobListProperties.getAppNameSpace(), defaultListableBeanFactory);
            BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(SpringJobScheduler.class).setScope(BeanDefinition.SCOPE_SINGLETON);
            factory.addConstructorArgValue(jobBean);
            factory.addConstructorArgValue(regCenter);
            factory.addConstructorArgValue(createJobConfiguration(setting));
            factory.addConstructorArgValue(new ConfigJobEventConfiguration());
            factory.addConstructorArgValue(new SimpleJobListener());
            factory.setLazyInit(false);
            factory.setInitMethodName("init");

            String scheduleBeanName = NAMESPACE + "/_" + jobListProperties.getAppNameSpace() + "_" + setting.getJobName() + SCHEDULE_SUB_FIX;
            defaultListableBeanFactory.registerBeanDefinition(scheduleBeanName, factory.getBeanDefinition());
            defaultListableBeanFactory.getBean(scheduleBeanName, SpringJobScheduler.class);

            log.info("===>注册完成job jobName:{} : config: {}",scheduleBeanName,  JsonUtil.toJson(setting));

        });


        return regCenter;
    }

    private LiteJobConfiguration createJobConfiguration(SingleJobProperties config) {
        final JobCoreConfiguration.Builder jobCoreBuilder = JobCoreConfiguration.newBuilder(config.getJobName(), config.getCron(), config.getShardingTotalCount());
        jobCoreBuilder.shardingItemParameters(config.getShardingItemParameters());
        jobCoreBuilder.jobParameter(config.getJobParameter());
        jobCoreBuilder.failover(config.isFailover());
        jobCoreBuilder.misfire(config.isMisfire());
        jobCoreBuilder.description(config.getJobDescription());
        jobCoreBuilder.jobProperties(JOB_EXCEPTION_HANDLER_KEY, ConfigJobExceptionHandler.class.getCanonicalName());
        final JobCoreConfiguration jobCoreConfiguration = jobCoreBuilder.build();

        final String jobClassName = config.getJobClass().getCanonicalName();
        JobTypeConfiguration jobTypeConfiguration = null;
        switch (config.getJobType()) {
            case TYPE_SIMPLE:
                jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClassName);
                break;
            case TYPE_DATA_FLOW:
                jobTypeConfiguration = new DataflowJobConfiguration(jobCoreConfiguration, jobClassName, true);
                break;
            default:
                break;
        }
        LiteJobConfiguration.Builder liteJobConfigurationBuilder = LiteJobConfiguration.newBuilder(jobTypeConfiguration);
        liteJobConfigurationBuilder.monitorExecution(true);
        liteJobConfigurationBuilder.overwrite(true);
        liteJobConfigurationBuilder.maxTimeDiffSeconds(MAX_TIME_DIFF_SECOND * 30);
        liteJobConfigurationBuilder.reconcileIntervalMinutes(10);
        liteJobConfigurationBuilder.jobShardingStrategyClass(AverageAllocationJobShardingStrategy.class.getCanonicalName());

        return liteJobConfigurationBuilder.build();
    }

    private Object registerSingleJobToSpringContainer(SingleJobProperties setting, String appNameSpace, DefaultListableBeanFactory defaultListableBeanFactory) {

        Class jobClass = setting.getJobClass();

        final String[] beanNamesForType = defaultListableBeanFactory.getBeanNamesForType(jobClass);

        Object jobBean = null;

        if (Objects.isNull(beanNamesForType) || beanNamesForType.length > 1) {
            jobBean = defaultListableBeanFactory.getBean(jobClass);
        } else {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(jobClass)
                    .setLazyInit(false).setScope(BeanDefinition.SCOPE_SINGLETON);
            String jobBeanName = appNameSpace.concat("_").concat(setting.getJobName());
            defaultListableBeanFactory.registerBeanDefinition(jobBeanName, beanDefinitionBuilder.getBeanDefinition());

            jobBean = defaultListableBeanFactory.getBean(jobBeanName, jobClass);
        }
        //注入配置信息
        ((AbstractJob) jobBean).setSetting(setting);
        return jobBean;
    }


    /**
     * 检查单个定时任务配置
     *
     * @param setting 单个定时任务
     */
    private void checkSingleJobProperties(SingleJobProperties setting) {

        Assert.notNull(setting, "单个定时任务不能为空");
        Assert.isTrue(!Strings.isNullOrEmpty(setting.getJobName()), "单个定时任务名称不能为空");
        final Class jobClass = setting.getJobClass();
        Assert.notNull(jobClass, "单个定时任务JobClass不能为空");
        Assert.isTrue(!Strings.isNullOrEmpty(setting.getCron()), "单个定时任务cron配置不能为空");
        String jobType = setting.getJobType();
        Assert.isTrue(!Strings.isNullOrEmpty(jobType), "作业类型不能为空");
        switch (jobType) {
            case TYPE_SIMPLE: {
                Assert.isAssignable(AbstractSimpleJob.class, jobClass);
                break;
            }
            case TYPE_DATA_FLOW: {
                Assert.isAssignable(AbstractDataFlowJob.class, jobClass);
                break;
            }
            default:
                throw new RuntimeException(String.format("unknow job type:%s", jobType));
        }
    }

    /**
     * 检查整体配置
     *
     * @param jobListProperties
     */
    private void checkJobListProperties(JobListProperties jobListProperties) {
        Assert.notNull(jobListProperties, "job.config不能为空");
        Assert.isTrue(!Strings.isNullOrEmpty(jobListProperties.getZkServerLists()), "zookeeper配置不能为空");
        Assert.isTrue(!Strings.isNullOrEmpty(jobListProperties.getAppNameSpace()), "appNameSpace配置不能为空");
        Arrays.stream(jobListProperties.getZkServerLists().split(",")).forEach(zkServer -> Validate.matchesPattern(zkServer, "^.+\\:\\d+$"));
        Assert.isTrue(!CollectionUtils.isEmpty(jobListProperties.getSettings()), "至少配置一个定时任务");
        final Map<String, List<SingleJobProperties>> groupMap = jobListProperties.getSettings().stream().collect(Collectors.groupingBy(SingleJobProperties::getJobName));
        groupMap.forEach((k, v) -> Assert.isTrue(v.size() == 1, String.format("jobName:%s必须唯一", k)));
        jobListProperties.getSettings().forEach(setting -> checkSingleJobProperties(setting));
        log.info("===job config is check success ======");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
