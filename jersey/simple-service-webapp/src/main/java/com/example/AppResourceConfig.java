package com.example;

import com.example.config.ExceptionHandler;
import com.example.config.ResponseHandler;
import com.example.config.ValidExceptionHandler;
import com.example.config.ObjectMapperResolver;
import com.example.config.ValidationConfigContextResolver;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

/**
 * 说明：提供命令行的方式启动web服务，并进行快速测试,快速开发
 * @author carter
 * 创建时间： 2019年12月04日 17:29
 **/
@ApplicationPath("/*")
public class AppResourceConfig extends ResourceConfig {

    public AppResourceConfig() {
        super();

        super.setApplicationName("simple-service-webapp");
        packages(AppResourceConfig.class.getPackage().getName());
        register(ObjectMapperResolver.class);
        register(JacksonFeature.withExceptionMappers());
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(ValidationConfigContextResolver.class);
        register(ResponseHandler.class);
        register(ExceptionHandler.class);
        register(ValidExceptionHandler.class);


    }


}
