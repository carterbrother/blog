package com.zengame.platweb.demo2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/12/15  16:32
 **/
@Service
@Slf4j
public class AsyncService {

    @Async
    public void async1(String name, DeferredResult<String> result) {
        log.info("async1 handle biz !");
        try {
            Assert.hasText(name, "name不能为空");
            if (name.toLowerCase().startsWith("a")) {
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return;
            }
            if (name.toLowerCase().startsWith("b")) {
                throw new RuntimeException("runtime exception");
            }

            result.setResult(" async1 success");
        }catch (Exception ex){
            log.error("业务处理出错",ex);
            result.setErrorResult(ex.getMessage());
        }
    }
}
