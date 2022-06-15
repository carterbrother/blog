//package com.xxx.demo.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.google.common.base.Strings;
//import com.lifesense.base.utils.SystemUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.flywaydb.core.Flyway;
//import org.flywaydb.core.api.configuration.ClassicConfiguration;
//import org.flywaydb.core.api.logging.LogFactory;
//import org.flywaydb.core.internal.logging.slf4j.Slf4jLogCreator;
//
///**
// * @author <a href="mailto:505847426@qq.com">李福春brother</a>
// * description Flayway配置信息, 非生产环境自动刷新数据库脚本
// * date 2019年05月10日 3:36 PM
// * Copyright (c) 李福春brother
// */
//@Slf4j
//public class FlyWayConfig {
//
//    private DruidDataSource dataSource;
//
//    public FlyWayConfig(DruidDataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//
//    public void init() {
//        try {
//            doRefreshDatabaseScript();
//        } catch (Exception e) {
//            log.error("===>update database error: {} ", e.getLocalizedMessage());
//        }
//    }
//
//    private void doRefreshDatabaseScript() {
//        if (!Strings.isNullOrEmpty(SystemUtils.getEnv()) && SystemUtils.isOnlineEnv()) {
//            return;
//        }
//        log.info("===>auto update database start");
//        final Slf4jLogCreator logCreator = new Slf4jLogCreator();
//        LogFactory.setLogCreator(logCreator);
//        LogFactory.setFallbackLogCreator(logCreator);
//        ClassicConfiguration classicConfiguration = new ClassicConfiguration();
//        classicConfiguration.setDataSource(dataSource);
//        classicConfiguration.setValidateOnMigrate(false);
//        classicConfiguration.setBaselineOnMigrate(true);
//        Flyway flyway = new Flyway(classicConfiguration);
//        flyway.baseline();
//        flyway.migrate();
//        log.info("===>auto  update database end");
//    }
//}
