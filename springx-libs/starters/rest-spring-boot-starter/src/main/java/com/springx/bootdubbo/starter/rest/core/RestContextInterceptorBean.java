package com.springx.bootdubbo.starter.rest.core;

import com.google.common.base.Defaults;
import com.google.common.base.Strings;
import com.springx.bootdubbo.common.bean.RestContextBean;
import com.springx.bootdubbo.common.enums.AppTypeEnum;
import com.springx.bootdubbo.common.exception.BaseException;
import com.springx.bootdubbo.common.util.RequestUtil;
import com.springx.bootdubbo.starter.rest.config.RestPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 自定义拦截器,
 * 用途：
 * 1，填充和清理RestContextBean;
 * 2.记录Rest的请求日志和正常响应日志
 * 3.执行登录校验
 * @date 2019年05月16日 6:15 PM
 * @Copyright (c) carterbrother
 */
@Slf4j
public class RestContextInterceptorBean implements HandlerInterceptor, ApplicationContextAware {

    private  RestPropertiesConfig restPropertiesConfig;
    private ApplicationContext applicationContext;

    public RestContextInterceptorBean( RestPropertiesConfig restPropertiesConfig) {
        this.restPropertiesConfig =   restPropertiesConfig;
        log.info("===>init RestContextInterceptorBean");
        if (Objects.isNull(restPropertiesConfig)){
            this.restPropertiesConfig = applicationContext.getBean(RestPropertiesConfig.class);
        }
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Integer appTypeInteger = getIntegerValue(request, RestContextBean.KeyDict.APP_TYPE_KEY);
        AppTypeEnum appType = Defaults.defaultValue(AppTypeEnum.class);
        if (Objects.nonNull(appTypeInteger)) {
            appType = AppTypeEnum.fromAppId(appTypeInteger);
        }

        final String requestId = getStringValue(request, RestContextBean.KeyDict.REQUEST_ID_KEY);
        final Long loginId = getLongValue(request, RestContextBean.KeyDict.LOGIN_ID_KEY);
        final Long userId = getLongValue(request, RestContextBean.KeyDict.USER_ID_KEY);


        MDC.put(RestContextBean.KeyDict.REQUEST_ID_KEY, requestId);
        MDC.put(RestContextBean.KeyDict.REQUEST_URL_KEY, request.getContextPath().concat(request.getRequestURI()));
        MDC.put(RestContextBean.KeyDict.APP_TYPE_KEY, Objects.isNull(appType) ? "" : appType.name());
        MDC.put(RestContextBean.KeyDict.SERVICE_NAME_KEY, System.getProperty(RestContextBean.KeyDict.SERVICE_NAME_KEY));
        MDC.put(RestContextBean.KeyDict.ENV_KEY, System.getProperty(RestContextBean.KeyDict.ENV_KEY));
        MDC.put(RestContextBean.KeyDict.USER_ID_KEY, Objects.toString(Objects.isNull(loginId) ? userId : loginId, ""));


        final RestContextBean restContext = RestContextBean.builder().appType(appType)
                .stopWatch(stopWatch)
                .requestId(requestId)
                .appVersion(getStringValue(request, RestContextBean.KeyDict.APP_VERSION_KEY))
                .userId(userId)
                .loginId(loginId)
                .accessToken(getStringValue(request, RestContextBean.KeyDict.ACCESS_TOKEN_KEY))
                .clientId(getStringValue(request, RestContextBean.KeyDict.CLIENT_ID_KEY))
                .osType(getIntegerValue(request, RestContextBean.KeyDict.OS_TYPE_KEY))
                .country(getStringValue(request, RestContextBean.KeyDict.COUNTRY_KEY))
                .province(getStringValue(request, RestContextBean.KeyDict.PROVINCE_KEY))
                .city(getStringValue(request, RestContextBean.KeyDict.CITY_KEY))
                .area(getStringValue(request, RestContextBean.KeyDict.AREA_KEY))
                .language(getStringValue(request, RestContextBean.KeyDict.LANGUAGE_KEY))
                .screenHeight(getIntegerValue(request, RestContextBean.KeyDict.SCREEN_HEIGHT_KEY))
                .screenWidth(getIntegerValue(request, RestContextBean.KeyDict.SCREEN_WIDTH_KEY))
                .loginType(getIntegerValue(request, RestContextBean.KeyDict.LOGIN_TYPE_KEY))
                .build();
        RestContextBean.setLocal(restContext);

        stopWatch.split();
        log.info("\n===param_request==={}", getRequestParam(request, restContext));

        if (Strings.isNullOrEmpty(requestId)) {
            throw new BaseException("requestId不能为空");
        }

        //识别@LoginIgnore注解,如果没有，进行登录校验
        final String loginCheckApiFullClassName = restPropertiesConfig.getLoginCheckApi();
//        LoginCheckApi loginCheckApi = applicationContext.getBean(loginCheckApiFullClassName, LoginCheckApi.class);
        //识别@PowerCheck注解，如果没有,跳过，如果有，校验功能权限

        //识别@PowerCheck注解是否需要获取数据权限SQL,如果没有，结束，如果有，则获取数据权限转换的sql语句

        return true;
    }

    private String getRequestParam(HttpServletRequest request, RestContextBean restContext) {
        Assert.notNull(request, "请求对象request不能为空");
        Assert.notNull(restContext, "restContext不能为空");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n===Url===").append(request.getContextPath().concat(request.getRequestURL().toString()));

        stringBuilder.append("\n===header==={\n");
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String headName = headerNames.nextElement();
            stringBuilder.append(headName).append("=").append(request.getHeader(headName)).append(",\n");
        }
        stringBuilder.append("}\n");

        stringBuilder.append("===param==={\n");
        request.getParameterMap().forEach((paramKey, paramValueArray) -> {
            stringBuilder.append(paramKey).append("=").append(Arrays.toString(paramValueArray)).append(",\n");
        });
        stringBuilder.append("}\n");
        stringBuilder.append("===RestContext==={\n").append(Objects.toString(restContext)).append("\n}");

        return stringBuilder.toString();
    }

    private String getStringValue(HttpServletRequest request, String appType) {
        String value = request.getHeader(appType);
        if (StringUtils.isEmpty(value)) {
            value = request.getParameter(appType);
        }
        return value;
    }

    private Long getLongValue(HttpServletRequest request, String paramName) {
        String paramStringValue = getStringValue(request, paramName);
        Long longValue = null;
        if (StringUtils.isNumeric(paramStringValue)) {
            try {
                longValue = Long.parseLong(paramStringValue);
            } catch (NumberFormatException e) {
                log.error("转换为长整数失败，{} ", paramName, e);
            }
        }
        return longValue;
    }

    private Integer getIntegerValue(HttpServletRequest request, String paramName) {
        Long longValue = getLongValue(request, paramName);
        if (Objects.isNull(longValue)) {
            return null;
        }
        return longValue.intValue();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


        Map<String, String> headMap = new HashMap<>(4);
        headMap.put("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        headMap.put("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type");
        headMap.put("Access-Control-Allow-Credentials", "true");
        headMap.put("Access-Control-Allow-Origin", "*");
        String refererUrl = RequestUtil.parseRequestOrigin(request.getHeader("Referer"), request.getHeader("Origin"));
        if (!Strings.isNullOrEmpty(refererUrl)) {
            headMap.put("Access-Control-Allow-Origin", refererUrl);
        }
        headMap.forEach((key, value) -> response.addHeader(key, value));

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        final StopWatch stopWatch = RestContextBean.getInstance().getStopWatch();
        log.info("\n===param_complete===\n===requestId==={}\n===method==={}\n===useTime==={}", RestContextBean.getInstance().getRequestId(), handler, stopWatch.getTime());

        stopWatch.stop();
        RestContextBean.clear();
        MDC.clear();


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext ;
    }
}
