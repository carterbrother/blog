package com.spring.story.zg.demo.safe.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * {@code @Author:} Carter.li
 * {@code @createdAt:} 2022/10/27  17:51
 **/
@Slf4j
public class AesUtil {

    /**
     * 指定密钥加密
     *
     * @param key 密钥
     */
    public static byte[] encryptBykey(String input, String key) {
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crypted;
    }

    /**
     * 指定密钥解密
     *
     * @param key 密钥
     */
    public static String decryptByKey(byte[] input, String key) {
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(input);
        } catch (Exception e) {
            log.error("aes解密错误:{}", e.getMessage(), e);
        }
        return new String(output);
    }


    public static void main(String[] args) {
        String sSrc = "{\"a\":\"carter.li\"}";
        String publicKey = "xxxxxxxxxxxxxxtestabc123456";


        String time = "123456";
        String loginAseKey = Md5Util.md5Encode(publicKey + "|" + time).substring(0, 16);

        //////////////////////////////////加密
        String res = Base64Util.encode(encryptBykey(sSrc, loginAseKey));
        log.info("miText: {}", res);


        //////////////////////////////////解密

        String data = decryptByKey(Base64Utils.decode(res.getBytes()), loginAseKey);
        log.info("text: {}", data);

    }


}
