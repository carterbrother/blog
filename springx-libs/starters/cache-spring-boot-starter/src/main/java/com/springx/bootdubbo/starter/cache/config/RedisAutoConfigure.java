package com.springx.bootdubbo.starter.cache.config;

import com.springx.bootdubbo.starter.cache.core.support.local.MapCacheProvider;
import com.springx.bootdubbo.starter.cache.core.support.redis.JedisProvider;
import com.springx.bootdubbo.starter.cache.core.support.redis.JedisProviderFactory;
import com.springx.bootdubbo.starter.cache.core.support.redis.cluster.JedisClusterProvider;
import com.springx.bootdubbo.starter.cache.core.support.redis.standard.JedisStandardProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description springboot starter的主配置类
 * @date 2019年02月25日 10:30 AM
 * @Copyright (c) carterbrother
 */
@Configuration
@ConditionalOnClass(JedisProvider.class)
@EnableConfigurationProperties(RedisPropertiesConfig.class)
public class RedisAutoConfigure implements BeanFactoryAware {

    protected static final Logger logger = LoggerFactory.getLogger(RedisAutoConfigure.class.getName());

    private BeanFactory beanFactory;

    @Resource
    private RedisPropertiesConfig redisPropertiesConfig;

    /**
     * 当ioc容器中没有JedisProvider的bean ，并且 配置文件中 有配置 redis.config.enabled=true ；才会装配这个 bean到容器
     *
     * @return
     */
    @Bean(destroyMethod = "destroy")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "redis.config", value = "enabled", havingValue = "true")
    public JedisProvider redisProvider() {
        assert Objects.nonNull(redisPropertiesConfig);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Objects.requireNonNull(redisPropertiesConfig.getPoolMaxIdle()));
        jedisPoolConfig.setMaxTotal(Objects.requireNonNull(redisPropertiesConfig.getPoolMaxTotal()));
        jedisPoolConfig.setMinIdle(Objects.requireNonNull(redisPropertiesConfig.getPoolMinIdle()));

        String[] serversArray = Objects.requireNonNull(redisPropertiesConfig.getServers()).split(RedisConstants.STRING_SPLIT_FLAG);
        final String mode = Objects.requireNonNull(redisPropertiesConfig.getMode());
        final Integer connTimeout = Objects.requireNonNull(redisPropertiesConfig.getConnTimeout());

        logger.info(String.format("register redisProvider[%s] starter on class: %s  ", mode, this.getClass().getSimpleName()));

        if (Objects.nonNull(beanFactory)) {
            JedisProviderFactory.setBeanFactory(beanFactory);
        }
        if (Objects.nonNull(redisPropertiesConfig)) {
            JedisProviderFactory.setRedisPropertiesConfig(redisPropertiesConfig);
        }
        if (Objects.equals(mode, RedisConstants.MODE_STANDARD)) {
            return new JedisStandardProvider(mode, jedisPoolConfig, serversArray, connTimeout);
        }

        if (Objects.equals(mode, RedisConstants.MODE_CLUSTER)) {
            return new JedisClusterProvider(mode, jedisPoolConfig, serversArray, connTimeout);
        }

        throw new RuntimeException(String.format("param mode :%s not support ", mode));

    }

    @Bean(destroyMethod = "close")
    @ConditionalOnClass(MapCacheProvider.class)
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MapCacheProvider mapCacheProvider() {
        return new MapCacheProvider();
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
