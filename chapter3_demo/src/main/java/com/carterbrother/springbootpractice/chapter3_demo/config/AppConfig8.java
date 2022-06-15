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
@ComponentScan({"com.carterbrother.springbootpractice.chapter3_demo.scope"})
public class AppConfig8 {

}
