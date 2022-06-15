package com.springbootpractice.demoshutdown.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 说明：配置tomcat容器的自动关闭回调
 * @author  carter
 * 创建时间： 2019年07月23日 10:12
 */
@Configuration
public class TomcatShutdownConfig {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final TomcatShutDownCustomizer tomcatShutDownCustomizer) {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers(tomcatShutDownCustomizer);
        tomcatServletWebServerFactory.setPort(8999);
        return tomcatServletWebServerFactory;
    }


}
