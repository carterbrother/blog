package com.springbootpractice.egproduct.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 进行网络检查
 * @date 2019年06月21日 18:30
 * @Copyright (c) carterbrother
 */
@Component
public class WWWHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

        if (ping()){
            builder.withDetail("message","可以正常连接互联网").up();
            return;
        }

        builder.withDetail("message","无法连接互联网").unknown();

    }

    private boolean ping() {

        try {
            return   InetAddress.getByName("www.baidu.com").isReachable(3000);
        } catch (IOException e) {
            return false;
        }

    }
}
