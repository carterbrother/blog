//package com.xxx.demo.biz.manager;
//
//
//import com.ctrip.framework.apollo.ConfigService;
//
///**
// * 说明：统一获取配置的类
// * @author 李福春
// * 创建时间： 2019年11月29日 14:26
// **/
//
//public final class ConfigManager {
//
//    private ConfigManager() {
//    }
//
//    private static final String NP_REDIS = "lx-doctor.redis";
//    private static final String NP_KAFKA = "lx-doctor.kafka";
//    private static final String NP_DUBBO = "lx-doctor.dubbo";
//
//
//    public static int getRedisPoolMaxTotal() {
//        return 0;
////        return Integer.parseInt(System.getProperty("redis.pool.maxTotal", "0"));
//    }
//
//    public static int getRedisPoolMaxIdle() {
//        return 0；
////        return ConfigService.getConfig(NP_REDIS).getIntProperty("redis.pool.maxIdle", 0);
//
//    }
//
//    public static int getRedisPoolMinIdle() {
//        return ConfigService.getConfig(NP_REDIS).getIntProperty("redis.pool.minIdle", 0);
//    }
//
//    /**
//     * standard:标准模式，cluster：集群模式，shard：分片模式
//     * @return redis的模式
//     */
//    public static String getRedisMode() {
//        return ConfigService.getConfig(NP_REDIS).getProperty("redis.mode", "standard");
//    }
//
//    /**
//     * 多个用“,”隔开
//     * @return redis的连接地址
//     */
//    public static String getRedisServers() {
//        return ConfigService.getConfig(NP_REDIS).getProperty("redis.servers", "");
//
//    }
//
//    /**
//     * @return redis的超时时间
//     */
//    public static Integer getRedisTimeout() {
//        return ConfigService.getConfig(NP_REDIS).getIntProperty("redis.conn.timeout", 0);
//
//    }
//
//    public static String getDbPassword() {
//        return ConfigService.getAppConfig().getProperty("master.db.password","");
//    }
//
//    public static String getDbUsername() {
//        return ConfigService.getAppConfig().getProperty("master.db.username","");
//    }
//
//    public static String getDbUrl() {
//        return ConfigService.getAppConfig().getProperty("master.db.url","");
//    }
//
//    public static String getDbDriverClassName() {
//        return ConfigService.getAppConfig().getProperty("db.driverClass","");
//    }
//
//    public static String getKafkaBootStrapServers() {
//        return ConfigService.getConfig(NP_KAFKA).getProperty("kafka.servers", "");
//
//    }
//
//    public static String getKafkaAcks() {
//        return ConfigService.getConfig(NP_KAFKA).getProperty("producer.acks", "");
//
//    }
//
//    public static String getKafkaRetries() {
//        return ConfigService.getConfig(NP_KAFKA).getProperty("kafka.producer.retries", "");
//
//    }
//
//    public static String getKafkaCompressionType() {
//        return ConfigService.getConfig(NP_KAFKA).getProperty("kafka.compression.type", "");
//
//    }
//}
