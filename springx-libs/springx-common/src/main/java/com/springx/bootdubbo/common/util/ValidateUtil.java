/**
 *
 */
package com.springx.bootdubbo.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 验证工具类
 *
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @description <br>
 * @date 2015年12月28日
 * @Copyright (c) 2015, lifesense.com
 */
public class ValidateUtil {

    private static Map<String, Pattern> patterns = new HashMap<String, Pattern>();
    /**
     * Email正则表达式
     */
    private static final String EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    private static final String EMAIL_NEW = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    /**
     * 电话号码正则表达式
     */
    private static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";

    /**
     * 国际电话号码正则表达式
     */
    private static final String INTERNATIONAL_MOBILE = "^\\s*\\+?\\s*(\\(\\s*\\d+\\s*\\)|\\d+)(\\s*-?\\s*(\\(\\s*\\d+\\s*\\)|\\s*\\d+\\s*))*\\s*$";
    /**
     * 手机号码正则表达式=^(13[0-9]|15[0-9]|18[0-9])\d{8}$
     */
    private static final String MOBILE = "^1([3|4|5|7|8|9])\\d{9}$";
    /**
     * Integer正则表达式 ^-?(([1-9]\d*$)|0)
     */
    private static final String INTEGER = "^-?(([1-9]\\d*$)|0)";

    /**
     * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
     */
    private static final String DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";

    /**
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁
     */
    private static final String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    /**
     * 邮编正则表达式  [0-9]\d{5}(?!\d) 国内6位邮编
     */
    private static final String CODE = "[0-9]\\d{5}(?!\\d)";
    /**
     * 用户名只能包含：数字、英文字母、下划线、中文
     */
    @SuppressWarnings("unused")
    private static final String USER_NAME_STR = "^[\u4e00-\u9fa5|\\w]{3,16}$";

    /**
     * 匹配由26个英文字母组成的字符串  ^[A-Za-z]+$
     */
    private static final String STR_ENG = "^[A-Za-z]+$";

    /*** 
     * 日期正则 支持： 
     *  YYYY-MM-DD  
     *  YYYY/MM/DD  
     *  YYYY_MM_DD  
     *  YYYYMMDD 
     *  YYYY.MM.DD的形式 
     */
    private static final String DATE_ALL = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)" +
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)" +
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)" +
            "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)" +
            "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)" +
            "(0?2)([-\\/\\._]?)(29)$)" +
            "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)" +
            "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|" +
            "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";
    /*** 
     * 日期正则 支持： 
     *  YYYY-MM-DD  
     */
    private static final String DATE_FORMAT1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    /**
     * URL正则表达式
     * 匹配 http www ftp
     */
    private static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" +
            "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" +
            "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" +
            "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
    /**
     * 身份证正则表达式
     */
    private static final String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
            "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
            "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";

    /**
     * 非安全字符
     */
    private static final String UNSAFE_STRINGS = ".*([#\\$%\\^&\\*'`\\*]+).*";

////------------------验证方法       

    /**
     * 判断字段是否为Email 符合返回ture
     *
     * @param str
     * @return boolean
     * 这个有问题
     */
    @Deprecated
    public static boolean isEmail(String str) {
        return Regular(str, EMAIL);
    }

    /**
     * 判断是否为电话号码 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isPhone(String str) {
        return Regular(str, PHONE);
    }

    /**
     * 判断是否为手机号码 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isMobile(String str) {
        return Regular(str, MOBILE);
    }

    /**
     * 判断是否为Url 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isUrl(String str) {
        return Regular(str, URL);
    }

    /**
     * 判断字段是否为数字 正负整数 正负浮点数 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isNumber(String str) {
        return Regular(str, DOUBLE);
    }

    /**
     * 判断字段是否为INTEGER  符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isInteger(String str) {
        return Regular(str, INTEGER);
    }

    /**
     * 判断字段是否为日期 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isDate(String str) {
        return Regular(str, DATE_ALL);
    }

    /**
     * 验证2010-12-10
     *
     * @param str
     * @return
     */
    public static boolean isDate1(String str) {
        return Regular(str, DATE_FORMAT1);
    }

    /**
     * 判断字段是否为年龄 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isAge(String str) {
        return Regular(str, AGE);
    }

    /**
     * 判断字段是否为身份证 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isIdCard(String str) {
        if (StringUtils.isBlank(str)) return false;
        if (str.trim().length() == 15 || str.trim().length() == 18) {
            return Regular(str, IDCARD);
        } else {
            return false;
        }
    }

    /**
     * 判断字段是否为邮编 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isZipCode(String str) {
        return Regular(str, CODE);
    }

    /**
     * 判断字符串是不是全部是英文字母
     *
     * @param str
     * @return boolean
     */
    public static boolean isEnglish(String str) {
        return Regular(str, STR_ENG);
    }

    /**
     * 判断字符串石否字符用户名格式（全部是英文字母+数字+下划线 ）
     *
     * @param str
     * @return boolean
     */
    public static boolean isUserName(String str) {
        if (str.length() < 2 || str.length() > 12) return false;
        //return Regular(str,USER_NAME_STR) ;  
        return isSafeString(str);
    }

    /**
     * 判断字符串石否字符用户名格式（全部是英文字母+数字+下划线 ）
     *
     * @param str
     * @return boolean
     */
    public static boolean isSafeString(String str) {
        return !Regular(str, UNSAFE_STRINGS);
    }

    /**
     * 匹配是否符合正则表达式pattern 匹配返回true
     *
     * @param str     匹配的字符串
     * @param pattern 匹配模式
     * @return boolean
     */
    private static boolean Regular(String str, String pattern) {
        if (null == str || str.trim().length() <= 0)
            return false;
        Pattern p = patterns.get(pattern);
        if (p == null) {
            p = Pattern.compile(pattern);
            patterns.put(pattern, p);
        }
        return p.matcher(str).matches();
    }


    /**
     * 判断字段是否为Email 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmailNew(String str) {
        return Regular(str, EMAIL_NEW);
    }

    /**
     * 是否国际号码
     *
     * @param str
     * @return boolean
     */
    public static boolean isInternationalMobile(String str) {
        return Regular(str, INTERNATIONAL_MOBILE);
    }

    public static void main(String[] args) {
        System.out.println(isInternationalMobile("+8000-13667863020"));
    }
}
