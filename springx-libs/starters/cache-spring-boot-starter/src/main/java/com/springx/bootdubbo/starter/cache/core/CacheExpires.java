package com.springx.bootdubbo.starter.cache.core;

import java.time.LocalDateTime;

/**
 * 缓存过期时间
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2016年2月28日
 * @Copyright (c) 2015, springx.com
 */
public class CacheExpires {

    public final static long IN_1MIN = 60;

    public final static long IN_3MINS = 60 * 3;

    public final static long IN_5MINS = 60 * 5;

    public final static long IN_1HOUR = 60 * 60;

    public final static long IN_1DAY = IN_1HOUR * 24;

    public final static long IN_1WEEK = IN_1DAY * 7;

    public final static long IN_1MONTH = IN_1DAY * 30;

    /**
     * 当前时间到今天结束相隔的秒
     *
     * @return
     */
    public static long todayEndSeconds() {
        return java.time.Duration.between(LocalDateTime.now(), LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0)).getSeconds();
    }

    /**
     * 当前时间到明天结束相隔的秒
     *
     * @return
     */
    public static long tormorrowEndSeconds() {
        return java.time.Duration.between(LocalDateTime.now(), LocalDateTime.now().plusDays(2).withHour(0).withMinute(0).withSecond(0)).getSeconds();
    }

}
