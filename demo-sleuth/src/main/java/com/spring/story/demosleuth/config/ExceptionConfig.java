package com.spring.story.demosleuth.config;


import com.google.common.base.Throwables;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author Carter.li
 * @createtime 2022/7/22 17:56
 */
@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(Exception.class)
    public R<Object> exceptionHandler(Exception ex) {

        return new R(0, ex.getMessage(), Throwables.getStackTraceAsString(Throwables.getRootCause(ex)), MDC.get("traceId"));
    }

}
