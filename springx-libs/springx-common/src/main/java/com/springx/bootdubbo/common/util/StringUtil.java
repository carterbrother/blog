package com.springx.bootdubbo.common.util;

import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 简单的字符串工具类
 * @date 2019年02月25日 1:44 PM
 * @Copyright (c) carterbrother
 */
public class StringUtil {

    private StringUtil() {
    }

    public static boolean isNotBlank(String param) {
        return Objects.nonNull(param) && !"".equals(param);
    }

    public static boolean isBlank(String param) {
        return !isNotBlank(param);
    }


    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static int toInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static long toLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException var4) {
                return defaultValue;
            }
        }
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0F);
    }

    public static float toFloat(String str, float defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    public static double toDouble(String str) {
        return toDouble(str, 0.0D);
    }

    public static double toDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException var4) {
                return defaultValue;
            }
        }
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static byte toByte(String str, byte defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Byte.parseByte(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    public static short toShort(String str, short defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Short.parseShort(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }
}
