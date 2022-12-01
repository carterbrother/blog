package com.spring.story.zg.demo.safe.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;


/**
 * {@code @Author:}: Carter.li
 * {@code @createdAt:}: 2022/10/27  18:50
 **/
public class Md5Util {

    /**
     * MD5摘要
     *
     * @param str
     * @return
     */
    public static String md5Digest(String str) {
        // 确定计算方法
        MessageDigest md5;
        String newstr = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            // 加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newstr;
    }

    public static String md5U(String inStr) {
        return md5Encode(inStr).toUpperCase();
    }

    /***
     * MD5加密 生成32位md5码
     *
     * @param inStr 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) {
        if(inStr==null) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray;
        StringBuffer hexValue = new StringBuffer();
        try {
            byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);

            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return hexValue.toString();
    }

}
