package com.lifesense.springboot.starter.redis.core.support.redis;

import com.lifesense.springboot.starter.redis.config.RedisConstants;
import com.lifesense.springboot.starter.redis.config.RedisPropertiesConfig;
import com.lifesense.springboot.starter.redis.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * redis静态工具类
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @description <br>
 * @date 2016年1月17日
 * @Copyright (c) 2015, lifesense.com
 */
public class JedisProviderFactory {

    protected static final Logger logger = LoggerFactory.getLogger(JedisProviderFactory.class.getName());

    private static BeanFactory beanFactory;
    private static RedisPropertiesConfig redisPropertiesConfig;

    private static JedisProvider<?, ?> defaultJedisProvider;
    @SuppressWarnings("rawtypes")
    private static Map<String, JedisProvider> jedisProviders = new ConcurrentHashMap<>();

    private JedisProviderFactory() {
    }

    public static void setBeanFactory(BeanFactory beanFactory) {
        JedisProviderFactory.beanFactory = beanFactory;
    }

    public static void setRedisPropertiesConfig(RedisPropertiesConfig redisPropertiesConfig) {
        JedisProviderFactory.redisPropertiesConfig = redisPropertiesConfig;
    }

    public static JedisProvider<?, ?> getJedisProvider(String groupName) {
        if (Objects.isNull(defaultJedisProvider)) {
            initFactoryFromSpring();
        }
        if (StringUtils.isNotBlank(groupName)) {
            if (jedisProviders.containsKey(groupName)) {
                return jedisProviders.get(groupName);
            } else {
                logger.warn(String.format("未找到group[%s]对应的redis配置，使用默认缓存配置", groupName));
            }
        }
        return defaultJedisProvider;
    }

    @SuppressWarnings("rawtypes")
    private static synchronized void initFactoryFromSpring() {
        if (Objects.isNull(defaultJedisProvider)) {
            //阻塞，直到spring初始化完成
            defaultJedisProvider = jedisProviders.get(RedisConstants.DEFAULT_GROUP_NAME);
            if (Objects.isNull(defaultJedisProvider)) {
                defaultJedisProvider = beanFactory.getBean(JedisProvider.class);
                jedisProviders.put(defaultJedisProvider.groupName(), defaultJedisProvider);
            }

            Assert.notNull(defaultJedisProvider, "无默认缓存配置，请指定一组缓存配置group为default");
        }
    }

    public static JedisCommands getJedisCommands(String groupName) {
        return (JedisCommands) getJedisProvider(groupName).get();
    }

    public static BinaryJedisCommands getBinaryJedisCommands(String groupName) {
        return (BinaryJedisCommands) getJedisProvider(groupName).getBinary();
    }

    public static BinaryJedisClusterCommands getBinaryJedisClusterCommands(String groupName) {
        return (BinaryJedisClusterCommands) getJedisProvider(groupName).getBinary();
    }

    public static JedisCommands getJedisClusterCommands(String groupName) {
        return (JedisCommands) getJedisProvider(groupName).get();
    }

    public static MultiKeyCommands getMultiKeyCommands(String groupName) {
        return (MultiKeyCommands) getJedisProvider(groupName).get();
    }

    public static MultiKeyBinaryCommands getMultiKeyBinaryCommands(String groupName) {
        return (MultiKeyBinaryCommands) getJedisProvider(groupName).get();
    }

    public static MultiKeyJedisClusterCommands getMultiKeyJedisClusterCommands(String groupName) {
        return (MultiKeyJedisClusterCommands) getJedisProvider(groupName).get();
    }

    public static MultiKeyBinaryJedisClusterCommands getMultiKeyBinaryJedisClusterCommands(String groupName) {
        return (MultiKeyBinaryJedisClusterCommands) getJedisProvider(groupName).get();
    }

    public static String currentMode(String groupName) {
        return getJedisProvider(groupName).mode();
    }

    public static boolean isStandard(String groupName) {
        return RedisConstants.MODE_STANDARD.equals(currentMode(groupName));
    }

    public static boolean isCluster(String groupName) {
        return RedisConstants.MODE_CLUSTER.equals(currentMode(groupName));
    }

    public static String getKeyPrefix() {
        if (Objects.isNull(redisPropertiesConfig)){
            return "";
        }
        return Optional.ofNullable(redisPropertiesConfig.getKeyPrefix()).orElse("");
    }
}
