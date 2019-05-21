package com.springx.bootdubbo.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrayUtil {

    public static final String GRAY_ENV_VARNAME = "lifesense_gray";

    public static String getGrayEnvVar() {
        return System.getenv(GRAY_ENV_VARNAME);
    }

    /**
     * 根据灰度百分比规则，根据用户ID判断用户是否进行灰度
     *
     * @param userId
     * @param percentage
     * @return
     */
    public static boolean isGrayUser(String userId, Integer percentage, Integer grayRemainder) {

        if (StringUtils.isBlank(userId) || percentage == null || grayRemainder == null) {
            return false;
        }

        boolean isGray = false;
        if (StringUtils.isNotBlank(userId) && percentage > 0 && grayRemainder >= 0) {
            Long code = getGrayCode(userId);
            if (code > 0) {
                Long remainder = code % Long.valueOf(percentage);//取模求余
                if (remainder.intValue() == grayRemainder) {//余数和灰度余数一致，则是灰度用户
                    isGray = true;
                }
            }
        }
        return isGray;
    }

    /**
     * 根据范围获取随机数
     *
     * @param percentage
     * @return
     */
    public static int getRandomNum(Integer percentage) {
        int num = 0;
        if (percentage != null && percentage > 0) {
            Random random = new Random();
            num = random.nextInt(percentage);
        }
        return num;
    }

    /**
     * 判断参数是否是数字
     *
     * @param param
     * @return
     */
    public static boolean isNumber(String param) {
        boolean isNum = false;
        if (StringUtils.isNotBlank(param)) {
            isNum = param.matches("[0-9]+");
        }
        return isNum;
    }

    /**
     * 判断参数是否包含字母
     *
     * @param param
     * @return
     */
    public static boolean containLetter(String param) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(param);
        return m.matches();
    }

    /**
     * 获取用户ID灰度编码
     *
     * @param userId
     * @return
     */
    private static Long getGrayCode(String userId) {
        Long code = 0L;
        if (StringUtils.isNotBlank(userId)) {
            boolean isNum = isNumber(userId);
            if (isNum) {
                code = Long.valueOf(userId);//数字类型，直接返回用户ID
            } else {
                code = Long.valueOf(userId.hashCode());//非数字，则取字符串的hashcode
            }
        }
        return code;
    }

    /**
     * 判断客户端版本是否需要更新
     *
     * @param appVersion
     * @param currentVersion
     * @return
     */
    public static boolean isClientNeedUpdate(String appVersion, String currentVersion) {
        boolean isNeed = false;
        if (StringUtils.isNotBlank(appVersion) && StringUtils.isNotBlank(currentVersion)) {
            //版本号包含字母属于异常版本号，不更新
            if (containLetter(appVersion) || containLetter(currentVersion)) {
                return false;
            }
            //判断版本号是否一致
            String[] versions = StringUtils.split(appVersion, ".");
            int vsLen = versions.length;
            String[] currentVersions = StringUtils.split(currentVersion, ".");
            int csLen = currentVersions.length;
            int i = 0;
            while (true) {
                int vs = Integer.valueOf(versions[i]);
                int cs = Integer.valueOf(currentVersions[i]);
                if (cs > vs) {
                    isNeed = true;
                    break;
                } else if (cs < vs) {
                    break;
                } else if (cs == vs) {
                    i++;
                    if (csLen - i == 0 || vsLen - i == 0) {
                        if (csLen > vsLen) {
                            isNeed = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return isNeed;
    }
}
