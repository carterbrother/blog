package com.spring.story.zg.demo.safe.util;

import org.springframework.util.Base64Utils;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/10/27  19:11
 **/
public class ApiUtil {

    /**
     * 请求body进行解密
     * @param requestBody
     * @param key
     * @return
     */
    public static String requestDecrypt(String requestBody,String key){
        return AesUtil.decryptByKey(Base64Utils.decode(requestBody.getBytes()), key);
    }

    /**
     * 响应信息进行加密
     * @param responseBody
     * @param key
     * @return
     */
    public static String responseEncrypt(String responseBody,String key){
        return Base64Util.encode(AesUtil.encryptBykey(responseBody, key));
    }
}
