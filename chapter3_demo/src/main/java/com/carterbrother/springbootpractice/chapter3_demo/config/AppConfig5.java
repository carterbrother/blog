package com.carterbrother.springbootpractice.chapter3_demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description ioc的配置类
 * @date 2019年02月14日 6:10 PM
 * @Copyright (c) carterbrother
 */
@Configuration
@ComponentScan(basePackages = {"com.carterbrother.springbootpractice.chapter3_demo.defi"} ,lazyInit = true
       )
public class AppConfig5 {


}
