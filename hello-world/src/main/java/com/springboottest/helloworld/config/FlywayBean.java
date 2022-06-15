package com.springboottest.helloworld.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Optional;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年04月18日 10:31 AM
 * @Copyright (c) carterbrother
 */
public class FlywayBean implements InitializingBean, DisposableBean {


    Flyway  flyway = null;

    public void destroy() throws Exception {
        Optional.ofNullable(flyway).ifPresent(item->item=null);

    }

    public void afterPropertiesSet() throws Exception {

    }
}
