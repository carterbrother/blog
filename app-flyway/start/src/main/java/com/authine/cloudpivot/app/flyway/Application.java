package com.authine.cloudpivot.app.flyway;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务的启动入口，只做组装
 * @author carter
 */
@EnableSwagger2Doc
//如果需要启用feign调用别的内部微服务接口，请解除下面一样的注解
//@EnableFeignClients(basePackages = "com.authine.mvp.app")
@SpringBootApplication(scanBasePackages = {
        "com.authine.cloudpivot.app.flyway",
        "com.alibaba.cola",
        "com.authine.mvp.controller.support"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
