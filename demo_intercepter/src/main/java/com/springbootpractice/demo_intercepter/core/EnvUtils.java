package com.springbootpractice.demo_intercepter.core;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 环境工具类
 * @date 2019年05月17日 6:00 PM
 * @Copyright (c) carterbrother
 */
public class EnvUtils {

    private EnvUtils(){}

    /**
     * 判断当前环境是不是生产环境
     * @return
     */
    public static boolean isOnline() {
        return false;
    }
}
