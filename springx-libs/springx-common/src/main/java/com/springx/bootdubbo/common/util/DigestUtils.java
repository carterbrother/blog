/**
 *
 */
package com.springx.bootdubbo.common.util;

import com.springx.bootdubbo.common.enums.ErrorCodeMsgEnum;
import com.springx.bootdubbo.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年11月13日
 * @Copyright (c) 2015, springx.com
 */
public class DigestUtils {


    /**
     *
     */
    private static final String PASSWORD_SALT = "AX33@#%66FGGG";
    private static final String CHAR_SET_UTF_8 = "UTF-8";
    private static final String MD5_NAME = "MD5";


    /**
     * MD5加密
     *
     * @param content
     * @return
     */
    public static String md5(Object content) {
        String keys = null;
        if (content == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance(MD5_NAME);
            byte[] bPass = String.valueOf(content).getBytes(CHAR_SET_UTF_8);
            md.update(bPass);
            keys = bytesToHexString(md.digest());
        } catch (NoSuchAlgorithmException aex) {
            System.out.println(aex);
        } catch (java.io.UnsupportedEncodingException uex) {
            System.out.println(uex);
        }
        return keys.toLowerCase();
    }

    public static String encodeBase64(String string) {
        try {
            return Base64Util.encodeToString(string.getBytes(CHAR_SET_UTF_8), false);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 帐号密码加密<br>
     * 对传入已加密的md5 值进行二次加密
     *
     * @param password 经过一轮md5加密的密文
     * @return
     */
    public static String passwordEncode(String password) {
        if (StringUtils.isBlank(password) || password.length() != 32) {
            throw new BaseException(ErrorCodeMsgEnum.PARAM_FORMAT_ERROR.code(), "传入初始值应为经过一轮md5加密的32密文");
        }
        String result = md5(PASSWORD_SALT + password);

        return result;
    }

    public static String decodeBase64(String string) {
        try {
            return new String(Base64Util.decodeFast(string.getBytes(CHAR_SET_UTF_8)));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static boolean checkPassword(String dbPassword, String password) {
        String encodePassword = passwordEncode(password);
        return StringUtils.equalsIgnoreCase(dbPassword, encodePassword);
    }

    public static void main(String[] args) {
        String md5 = DigestUtils.md5("123456");
        System.out.println(passwordEncode(md5));
    }
}
