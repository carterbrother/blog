package com.lifesense.sz.research_formimageread.config;

import com.baidu.aip.ocr.AipOcr;
import com.lifesense.sz.research_formimageread.configfile.AIPConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description AIP访问客户端的配置
 * @date 2019年02月18日 12:47 PM
 * @Copyright (c) carterbrother
 */
@Configuration
public class AIPConfigBean {

    @Autowired
    private AIPConfig aipConfig;


    @Bean
    public AipOcr aipOcr(){

       AipOcr aipOcr = new AipOcr(aipConfig.getAppId(),aipConfig.getApiKey(),aipConfig.getSecretKey());

       aipOcr.setConnectionTimeoutInMillis(2000);
       aipOcr.setSocketTimeoutInMillis(60000);

       return aipOcr;
    }


    @Bean(destroyMethod = "shutdown")
    @Qualifier("aipThreadPool")
    public ExecutorService aipExecutorService() {
        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                4 * Runtime.getRuntime().availableProcessors(), 2, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10000),
                r -> {
                        final Thread thread = new Thread(r);
                        thread.setName("aipExecutor-%d");
                        return thread;
                     }, new ThreadPoolExecutor.AbortPolicy()
        );

        return executorService;
    }


    @Bean
    public RetryTemplate retryTemplate(){

        RetryTemplate retryTemplate = new RetryTemplate();

        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(600));
        final FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000L);
        retryTemplate.setBackOffPolicy(backOffPolicy);


        return retryTemplate;
    }


}
