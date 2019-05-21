package com.springx.bootdubbo.common.util;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author Grit
 */
public final class UUIDUtil {

    private UUIDUtil() {
    }

    /**
     * 随机生成UUID，不含“-”，长度32
     *
     * @return 随机生成的UUID字符串，不含“-”，长度32
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}