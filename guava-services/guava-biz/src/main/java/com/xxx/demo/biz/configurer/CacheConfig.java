package com.xxx.demo.biz.configurer;

import org.springframework.context.annotation.Configuration;

/**
 * 说明: 缓存的配置
 * @author 李福春
 * 创建时间： 2019年11月29日 14:24
 **/
@Configuration
public class CacheConfig {

//
//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(ConfigManager.getRedisPoolMaxTotal());
//        jedisPoolConfig.setMaxIdle(ConfigManager.getRedisPoolMaxIdle());
//        jedisPoolConfig.setMinIdle(ConfigManager.getRedisPoolMinIdle());
//        return jedisPoolConfig;
//    }
//
//    @Bean
//    public JedisProviderFactoryBean jedisProviderFactoryBean() {
//
//        JedisProviderFactoryBean jedisProviderFactoryBean = new JedisProviderFactoryBean();
//        jedisProviderFactoryBean.setJedisPoolConfig(jedisPoolConfig());
//        jedisProviderFactoryBean.setMode(ConfigManager.getRedisMode());
//        jedisProviderFactoryBean.setServers(ConfigManager.getRedisServers());
//        jedisProviderFactoryBean.setTimeout(ConfigManager.getRedisTimeout());
//
//        return jedisProviderFactoryBean;
//
//    }

}
