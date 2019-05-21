package com.springx.bootdubbo.common.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数值类型处理
 */
public class NumberUtil {

    public static Long parseLong(Object object) {
        Long value = null;
        if (object == null) {
            return value;
        }
        try {
            value = Long.parseLong(object.toString());
        } catch (NumberFormatException e) {

        }
        return value;
    }

    public static Long parseLong(Object object, Long defaultValue) {
        Long value = defaultValue;
        if (object == null) {
            return value;
        }
        try {
            value = Long.parseLong(object.toString());
        } catch (NumberFormatException e) {

        }
        return value;
    }

    public static Integer parseInt(Object object) {
        Integer value = null;
        if (object == null) {
            return value;
        }
        try {
            value = Integer.parseInt(object.toString());
        } catch (NumberFormatException e) {

        }
        return value;
    }

    public static Integer parseInt(Object object, Integer defaultValue) {
        Integer value = defaultValue;
        if (object == null) {
            return value;
        }
        try {
            value = Integer.parseInt(object.toString());
        } catch (NumberFormatException e) {

        }
        return value;
    }

    /**
     * 对象转时间，对象要是数值型才能转换
     *
     * @param str
     * @return
     */
    public static Date parseDate(Object str) {
        Date date = null;
        if (str == null) {
            return date;
        }
        Long num = NumberUtil.parseLong(str);
        if (num == null) {
            return date;
        }
        return new Date(num);
    }

    /**
     * 非空且非零，返回true
     *
     * @param number
     * @return
     */
    public static boolean notNullAndZero(Number number) {
        if (number == null) {
            return false;
        }
        if (number.doubleValue() == 0) {
            return false;
        }
        return true;
    }

    public static boolean isNullOrZero(Number number) {
        return !notNullAndZero(number);
    }

    static DecimalFormat df_0 = new DecimalFormat("###.0");
    static DecimalFormat df_00 = new DecimalFormat("###.00");

    /**
     * 格式化 浮点数
     *
     * @param number
     * @return
     */
    public static float format(float number) {
        return Float.parseFloat(df_00.format(number));
    }

    public static Double format(Double value) {
        return Double.parseDouble(df_00.format(value));
    }

    /**
     * Double 2 Integer
     *
     * @param x
     * @return
     */
    public static Integer DoubleToInteger(Double x) {

        return Integer.parseInt(new java.text.DecimalFormat("0").format(x));
    }

    /**
     * 产生随机数 传入 最大 最小 范围
     *
     * @param max
     * @param min
     * @return
     */
    public static Integer RandomInt(Integer max, Integer min) {

        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * 截取数字
     *
     * @param content
     * @return
     */
    public static int getNumbers(String content) {
        if (content == null || content.equals(""))
            return 0;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {

            return Integer.parseInt(matcher.group(0));

        }
        return 0;
    }

}