package com.carterbrother.springbootpractice.chapter3_demo.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description ioc的配置类
 * @date 2019年02月14日 6:10 PM
 * @Copyright (c) carterbrother
 */
@Configuration
public class AppConfig7 {

       private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AppConfig7.class);
       @Bean
       @Conditional(AppConfig7.MyConditional.class)
       public DataSource dataSource()
       {
              return new DataSource() {
                     @Override
                     public Connection getConnection() throws SQLException {
                            return null;
                     }

                     @Override
                     public Connection getConnection(String username, String password) throws SQLException {
                            return null;
                     }

                     @Override
                     public <T> T unwrap(Class<T> iface) throws SQLException {
                            return null;
                     }

                     @Override
                     public boolean isWrapperFor(Class<?> iface) throws SQLException {
                            return false;
                     }

                     @Override
                     public PrintWriter getLogWriter() throws SQLException {
                            return null;
                     }

                     @Override
                     public void setLogWriter(PrintWriter out) throws SQLException {

                     }

                     @Override
                     public void setLoginTimeout(int seconds) throws SQLException {

                     }

                     @Override
                     public int getLoginTimeout() throws SQLException {
                            return 0;
                     }

                     @Override
                     public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                            return null;
                     }
              };
       }

      static   class MyConditional implements Condition{

              @Override
              public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
                     logger.info("模拟不匹配，不装配 DataSourceBean");
                     return false;
              }
       }

}
