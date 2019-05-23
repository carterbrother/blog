package com.springx.backend.server.api.demo.provider;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 暴露给内部应用的Dubbo接口
 * @date 2019年05月22日 4:06 PM
 * @Copyright (c) carterbrother
 */
public interface DemoAPI {
    /**
     * 计算两个数的和
     * @param a  数a,null = 0
     * @param b  数b,null = 0
     * @return  a+b的和
     *
     */
    Integer add(Integer a , Integer b);

}
