package com.springx.bootdubbo.starter.cache.core.utils;


import com.springx.bootdubbo.starter.cache.core.utils.serial.KryoPoolSerializer;

import java.io.IOException;

/**
 * 对象序列化工具
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年10月30日
 * @Copyright (c) 2015, springx.com
 */
public class SerializeUtils {

    static KryoPoolSerializer serializer = new KryoPoolSerializer();

    /**
     * 序列化
     *
     * @param object 需要序列化的对象
     * @return
     */
    public static byte[] serialize(Object object) {
        try {
            return serializer.serialize(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化
     *
     * @param bytes 需要被反序列化的数据
     * @return
     */
    public static Object deserialize(byte[] bytes) {
        try {
            return serializer.deserialize(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}