package com.springx.bootdubbo.common.util;

import org.springframework.util.Assert;

/**
 * 断言封装
 */
public final class AssertUtil {

    private AssertUtil() {
    }


    public static void notNull(Object param, String assertMsg) {
        Assert.notNull(param, assertMsg);
    }

}
