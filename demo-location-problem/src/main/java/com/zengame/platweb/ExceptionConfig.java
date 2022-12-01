package com.zengame.platweb;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 统一异常兜底处理
 *
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/30  14:57
 **/
@RestControllerAdvice
@Slf4j
public class ExceptionConfig {

    @ExceptionHandler(value = {Throwable.class, Exception.class, RuntimeException.class})
    @ResponseBody
    public Object excp(Throwable throwable) {
        Map<String, Object> res = new LinkedHashMap<>(4);

        res.put("code", 500);
        res.put("message", ExceptionUtil.getRootCauseMessage(throwable));
        res.put("data", ExceptionUtil.stacktraceToString(throwable));
        log.error("", throwable);
        return res;
    }
}
