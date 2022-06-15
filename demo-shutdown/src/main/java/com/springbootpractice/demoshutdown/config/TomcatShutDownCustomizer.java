package com.springbootpractice.demoshutdown.config;
/*
 * 说明：tomcat关闭动作的回调
 * @author  carter
 * 创建时间： 2019年07月23日 10:01
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TomcatShutDownCustomizer implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final int TIME_OUT = 30;
    private volatile Connector connector;


    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        //暂停接受外部请求
        this.connector.pause();
        final Executor executor = this.connector.getProtocolHandler().getExecutor();

        if (Objects.isNull(executor)) {
            return;
        }

        if (executor instanceof ThreadPoolExecutor) {
            log.warn("WEB 应用开始关闭");
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;

                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(TIME_OUT, TimeUnit.SECONDS)) {
                    log.warn("WEB应用等待时间超过最大时长{}秒，即将强行关闭！", TIME_OUT);
                    threadPoolExecutor.shutdownNow();
                    if (!threadPoolExecutor.awaitTermination(TIME_OUT, TimeUnit.SECONDS)) {
                        log.error("WEB应用强制关闭失败");
                    }else{
                        log.warn("WEB应用强制关闭关闭成功");

                    }

                }else{
                    log.warn("WEB应用关闭成功");
                }

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }


    }
}
