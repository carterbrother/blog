package com.spring.story.zg.demo.safe.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * {@code @Author:}: Carter.li
 * {@code @createdAt:}: 2022/10/27  18:01
 **/
@Slf4j
public class Base64Util {

    /**
     * 编码
     *
     * @param bstr
     * @return String
     */
    public static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    public static String encode_nowap(byte[] bstr) {

        return new sun.misc.BASE64Encoder().encode(bstr).replaceAll("\r|\n", "");

    }

    public static MultipartFile decodeFile(String baser64) {
        return BASE64DecodedMultipartFile.base64ToMultipart(baser64);
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            log.error("解密出错：{}", e.getMessage(), e);
        }

        return bt;
    }

}
