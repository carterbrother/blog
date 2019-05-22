package com.springx.bootdubbo.common.enums;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 微服务的枚举
 * @date 2019年05月21日 7:20 PM
 * @Copyright (c) carterbrother
 */
public enum ServiceEnum implements BaseEnum<String> {
    serviceA("用户服务");


    private String serverName;

    ServiceEnum(String serviceName) {
        this.serverName = serviceName;
    }

    /**
     * 编码
     *
     * @return
     */
    @Override
    public String code() {
        return this.name();
    }
}
