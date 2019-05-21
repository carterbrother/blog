package com.springx.bootdubbo.common.util;

import java.util.Collection;
import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 集合相关的工具类
 * @date 2019年05月21日 6:24 PM
 * @Copyright (c) carterbrother
 */
public final class CollectionUtil {

    private CollectionUtil() {
    }

    /**
     * 判断集合是否为空 true为null或者空，false则有元素
     *
     * @param objectCollection 对象集合T
     * @return true为null或者空，false则有元素
     */
    public static <T> boolean isEmpty(Collection<T> objectCollection) {
        if (Objects.isNull(objectCollection)) {
            return true;
        }
        return objectCollection.isEmpty();
    }
}
