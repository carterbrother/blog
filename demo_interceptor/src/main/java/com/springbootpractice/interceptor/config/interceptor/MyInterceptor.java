package com.springbootpractice.interceptor.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 说明：我的同步拦截器
 * @author carter
 * 创建时间： 2020年02月19日 11:05 下午
 **/
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    private static ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatchThreadLocal.set(stopWatch);
        stopWatch.start();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        final StopWatch stopWatch = stopWatchThreadLocal.get();
        stopWatch.stop();
        stopWatch.start();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        final StopWatch stopWatch = stopWatchThreadLocal.get();
        stopWatch.stop();

        log.info("url:{},method:{},totalCost:{},controllerCost:{},viewCost:{}",request.getRequestURI()
        ,handler,
                stopWatch.getTotalTimeNanos(),
                stopWatch.getTotalTimeNanos()-stopWatch.getLastTaskTimeNanos(),
                stopWatch.getLastTaskTimeNanos());

    }
}
