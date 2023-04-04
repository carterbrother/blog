package com.zengame.platweb.demo2.controller;

import com.zengame.platweb.demo2.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/12/15  16:30
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncController {

    private final AsyncService asyncService;

    @GetMapping("/async1")
    public DeferredResult<String> async1(@RequestParam("name") String name){
        log.info("start handle  async1 !");
        DeferredResult<String> result = new DeferredResult<>(6000L,"timeout");

        asyncService.async1(name,result);

        result.onCompletion(()->log.info("async1处理完毕"));

        result.onError(throwable -> {
            log.error("出错了,{}",throwable);
            result.setErrorResult("error");
        });
        return result;
    }


}
