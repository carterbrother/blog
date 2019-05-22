package com.springx.bootdubbo.common.enums;

import com.springx.bootdubbo.common.exception.BaseException;

/**
 * 所有枚举的父接口
 *
 * @param <C> 泛型
 * @author lifesense-szyf01
 */
public interface BaseEnum<C> {
    /**
     * 编码
     *
     * @return
     */
    C code();

    public static <T extends BaseEnum<?>> T parse(Class<T> enumType, Object code) {
        T[] enums = enumType.getEnumConstants();
        for (T t : enums) {
            if (t.code().equals(code)) {
                return t;
            }
        }
        throw new BaseException("No enum constant " + enumType.getCanonicalName() + "." + code);
    }


}
