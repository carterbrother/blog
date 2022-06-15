package com.springbootpractice.demo_intercepter.core;

import com.google.common.base.Defaults;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 自定义拦截器
 * @date 2019年05月16日 6:15 PM
 * @Copyright (c) carterbrother
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("invoke preHandle method ");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Integer appTypeInteger = getIntegerValue(request,RestContext.KeyDict.APP_TYPE_KEY);
        AppType appType = Defaults.defaultValue(AppType.class);
        if (Objects.nonNull(appTypeInteger)){
            appType = AppType.fromAppId(appTypeInteger);
        }

        final String requestId = getStringValue(request, RestContext.KeyDict.REQUEST_ID_KEY);
        final Long loginId = getLongValue(request, RestContext.KeyDict.LOGIN_ID_KEY);
        final Long userId = getLongValue(request, RestContext.KeyDict.USER_ID_KEY);


        MDC.put(RestContext.KeyDict.REQUEST_ID_KEY,requestId);
        MDC.put(RestContext.KeyDict.REQUEST_URL_KEY,request.getContextPath().concat(request.getRequestURI()));
        MDC.put(RestContext.KeyDict.APP_TYPE_KEY, Objects.isNull(appType)?"":appType.name());
        MDC.put(RestContext.KeyDict.SERVICE_NAME_KEY,System.getProperty(RestContext.KeyDict.SERVICE_NAME_KEY));
        MDC.put(RestContext.KeyDict.ENV_KEY,System.getProperty(RestContext.KeyDict.ENV_KEY));
        MDC.put(RestContext.KeyDict.USER_ID_KEY, Objects.toString(Objects.isNull(loginId)?userId:loginId,""));



        final RestContext restContext = RestContext.builder().appType(appType)
                .stopWatch(stopWatch)
                .requestId(requestId)
                .appVersion(getStringValue(request, RestContext.KeyDict.APP_VERSION_KEY))
                .userId(userId)
                .loginId(loginId)
                .accessToken(getStringValue(request, RestContext.KeyDict.ACCESS_TOKEN_KEY))
                .clientId(getStringValue(request, RestContext.KeyDict.CLIENT_ID_KEY))
                .osType(getIntegerValue(request, RestContext.KeyDict.OS_TYPE_KEY))
                .country(getStringValue(request, RestContext.KeyDict.COUNTRY_KEY))
                .province(getStringValue(request, RestContext.KeyDict.PROVINCE_KEY))
                .city(getStringValue(request, RestContext.KeyDict.CITY_KEY))
                .area(getStringValue(request, RestContext.KeyDict.AREA_KEY))
                .language(getStringValue(request, RestContext.KeyDict.LANGUAGE_KEY))
                .screenHeight(getIntegerValue(request, RestContext.KeyDict.SCREEN_HEIGHT_KEY))
                .screenWidth(getIntegerValue(request, RestContext.KeyDict.SCREEN_WIDTH_KEY))
                .loginType(getIntegerValue(request, RestContext.KeyDict.LOGIN_TYPE_KEY))
                .build();
        RestContext.setLocal(restContext);

        stopWatch.split();
        log.info("\n===param_request==={}",getRequestParam(request,restContext));

        if (Strings.isNullOrEmpty(requestId)){
            throw new BaseException("requestId不能为空");
        }

        return true;
    }

    private String getRequestParam(HttpServletRequest request, RestContext restContext) {
        Assert.notNull(request,"请求对象request不能为空");
        Assert.notNull(restContext,"restContext不能为空");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n===Url===").append(request.getContextPath().concat(request.getRequestURL().toString()));

        stringBuilder.append("\n===header==={\n");
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            final String headName = headerNames.nextElement();
            stringBuilder.append(headName).append("=").append(request.getHeader(headName)).append(",\n");
        }
        stringBuilder.append("}\n");

        stringBuilder.append("===param==={\n");
        request.getParameterMap().forEach((paramKey,paramValueArray)->{
            stringBuilder.append(paramKey).append("=").append(Arrays.toString(paramValueArray)).append(",\n");
        });
        stringBuilder.append("}\n");
        stringBuilder.append("===RestContext==={\n").append(Objects.toString(restContext)).append("\n}");

        return stringBuilder.toString();
    }

    private String getStringValue(HttpServletRequest request, String appType) {
        String value = request.getHeader(appType);
       if (StringUtils.isEmpty(value)){
           value = request.getParameter(appType);
       }
        return value;
    }

    private Long getLongValue(HttpServletRequest request, String paramName){
        String paramStringValue = getStringValue(request,paramName);
        Long longValue = null;
        if(StringUtils.isNumeric(paramStringValue)){
            try{
                longValue = Long.parseLong(paramStringValue);
            }catch (NumberFormatException e){
                log.error("转换为长整数失败，{} ",paramName , e);
            }
        }
        return longValue;
    }

    private Integer getIntegerValue(HttpServletRequest request, String paramName){
        Long longValue = getLongValue(request,paramName);
        if (Objects.isNull(longValue)){
            return null;
        }
        return longValue.intValue();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("invoke postHandle method ");

        Map<String, String> headMap = new HashMap<>(4);
        headMap.put("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        headMap.put("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type");
        headMap.put("Access-Control-Allow-Credentials", "true");
        headMap.put("Access-Control-Allow-Origin", "*");
        String refererUrl = parseRequestOrigin(request.getHeader("Referer"), request.getHeader("Origin"));
        if (!Strings.isNullOrEmpty(refererUrl)) {
            headMap.put("Access-Control-Allow-Origin", refererUrl);
        }
        headMap.forEach((key, value) -> response.addHeader(key, value));

    }


    public static String parseRequestOrigin(String... urls){
        if(urls==null || urls.length==0){
            return null;
        }
        for(int i=0;i<urls.length;i++){
            String referer=urls[i];
            if(StringUtils.isEmpty(referer)){
                continue;
            }
            URL url;
            try {
                url = new URL(referer);
            } catch (MalformedURLException e) {
                continue;
            }
            String host = url.getHost();
            String protocol = url.getProtocol();
            if (StringUtils.startsWith(protocol, "http")) {
                if(url.getPort()>0&& url.getPort()!=80){
                    return protocol + "://" + host+":"+url.getPort();
                }else{
                    return protocol + "://" + host;
                }
            }
        }
        return null;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("invoke afterCompletion method ");

        final StopWatch stopWatch = RestContext.getInstance().getStopWatch();
        log.info("\n===param_complete===\n===requestId==={}\n===method==={}\n===useTime==={}\n===exception==={}", RestContext.getInstance().getRequestId(), handler , stopWatch.getTime(), ex);

        stopWatch.stop();
        RestContext.clear();
        MDC.clear();


    }
}
