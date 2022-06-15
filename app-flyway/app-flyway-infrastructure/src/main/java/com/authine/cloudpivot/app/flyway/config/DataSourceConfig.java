package com.authine.cloudpivot.app.flyway.config;

import  com.authine.cloudpivot.app.flyway.repository._Repository_;
import com.authine.mvp.lib.mybatis.config.AbstractMybatisConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author carter
 * create_date  2020/6/2 18:18
 * description     mybtis增强配置
 */
@Configuration
public class DataSourceConfig extends AbstractMybatisConfig {
    @Override
    protected String getBasePackage() {
        return _Repository_.class.getPackage().getName();
    }
}
