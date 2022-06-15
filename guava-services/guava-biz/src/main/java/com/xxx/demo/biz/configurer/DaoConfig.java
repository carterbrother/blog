//package com.xxx.demo.config;
//
//import com.leb.libs.opensource.support.mybatis.LsBaseMapper;
//import com.zaxxer.hikari.HikariDataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import tk.mybatis.spring.mapper.MapperScannerConfigurer;
//
//import java.io.IOException;
//
///**
// * 说明：dao的xml配置代码化
// * @author 李福春
// * 创建时间： 2019年11月29日 15:00
// **/
//
//@Configuration
//public class DaoConfig {
//
////    @Autowired
////    private static HikariDataSource hikariDataSource;
////
////
////
////    @Bean(/*initMethod = "init",*/ destroyMethod = "close")
////    public static DataSource routeDataSource() {
////        return hikariDataSource;
////    }
//
//
////    @Bean
////    public TransactionTemplate transactionTemplate() {
////        TransactionTemplate transactionTemplate = new TransactionTemplate();
////        transactionTemplate.setTransactionManager(transactionManager());
////        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
////        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
////        return transactionTemplate;
////    }
//
////    @Bean(initMethod = "init")
////    public FlyWayConfig flyWayConfig() {
////        return new FlyWayConfig(routeDataSource());
////    }
//
//    @Bean
//    public static SqlSessionFactoryBean routeSqlSessionFactory(HikariDataSource dataSource) {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//
//        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//
//        try {
////            sqlSessionFactoryBean.setConfigLocation(pathMatchingResourcePatternResolver.getResource("classpath:mybatis-configuration.xml"));
//            sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources("classpath*:mapper/*Mapper.xml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.xxx.demo.dao.entity");
////        final PluginEntrypoint pluginEntrypoint = new PluginEntrypoint();
////        pluginEntrypoint.setInterceptorHandlers(Collections.emptyList());
////        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pluginEntrypoint});
//        return sqlSessionFactoryBean;
//    }
//
//
//    @Bean
//    public static MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("routeSqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.xxx.demo.dao.mapper");
//        mapperScannerConfigurer.setMarkerInterface(LsBaseMapper.class);
//
//        return mapperScannerConfigurer;
//    }
//
//
//}
