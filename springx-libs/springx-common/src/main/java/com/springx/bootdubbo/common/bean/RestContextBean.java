package com.springx.bootdubbo.common.bean;

import com.springx.bootdubbo.common.enums.AppTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 封装一些请求信息中的公共信息
 * @date 2019年05月17日 10:41 AM
 * @Copyright (c) carterbrother
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class RestContextBean {
    /**
     * 计时器，计算每一步的时间
     */
    private StopWatch stopWatch;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 应用类型，标识系统
     */
    private AppTypeEnum appType;
    /**
     * app的版本号
     */
    private String appVersion;
    /**
     * 用户ID，如果url或者header中有
     */
    private Long userId;
    /**
     * 登录的用户ID，登录才有
     */
    private Long loginId;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 操作系统类型
     */
    private Integer osType;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区域
     */
    private String area;
    /**
     * 语言
     */
    private String language;
    /**
     * 屏幕宽度
     */
    private Integer screenWidth;
    /**
     * 屏幕高度
     */
    private Integer screenHeight;
    /**
     * 登录类型,1普通账号登录 2 微信登录 3 其它登录
     */
    private Integer loginType;
    /**
     * 授权码，登录才有
     */
    private String accessToken;


    private static final ThreadLocal<RestContextBean> LOCAL = ThreadLocal.withInitial(RestContextBean::new);

    /**
     * 设置实例
     *
     * @param restContext
     */
    public static void setLocal(RestContextBean restContext) {
        LOCAL.set(restContext);
    }


    /**
     * 获取实例
     *
     * @return
     */
    public static RestContextBean getInstance() {
        return LOCAL.get();
    }

    /**
     * 清理实例
     */
    public static void clear() {
        LOCAL.remove();
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");


        Stream.of(this.getClass().getDeclaredFields()).filter(item -> Arrays.asList("log", "LOCAL").stream().noneMatch(xx -> Objects.equals(item.getName(), xx)))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        final Object fieldValue = field.get(this);
                        if (Objects.nonNull(fieldValue)) {
                            stringBuilder.append("\n").append(field.getName()).append("=").append(Objects.toString(fieldValue)).append(",");
                        }
                    } catch (Exception ex) {
                        log.error("获取属性失败：{}", ex);
                    }

                });

        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }


    public static class KeyDict {
        public static final String REQUEST_ID_KEY = "requestId";
        public static final String APP_TYPE_KEY = "appType";
        public static final String APP_VERSION_KEY = "appVersion";
        public static final String USER_ID_KEY = "userId";
        public static final String LOGIN_ID_KEY = "loginId";
        public static final String CLIENT_ID_KEY = "clientId";
        public static final String OS_TYPE_KEY = "osType";
        public static final String COUNTRY_KEY = "country";
        public static final String PROVINCE_KEY = "province";
        public static final String CITY_KEY = "city";
        public static final String AREA_KEY = "area";
        public static final String LANGUAGE_KEY = "language";
        public static final String SCREEN_WIDTH_KEY = "screenWidth";
        public static final String SCREEN_HEIGHT_KEY = "screenHeight";
        public static final String LOGIN_TYPE_KEY = "loginType";
        public static final String ACCESS_TOKEN_KEY = "accessToken";

        public static final String REQUEST_URL_KEY = "requestUrl";
        public static final String SERVICE_NAME_KEY = "serviceName";
        public static final String ENV_KEY = "env";
        public static final String STOP_WATCH_KEY = "stopWatch";

    }
}
