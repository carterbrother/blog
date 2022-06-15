package com.xxx.demo.biz.service.impl;

import com.xxx.demo.api.dubbo.api.ISaasUserApi;
import org.springframework.stereotype.Service;

/**
 * 说明:内部系统的api实现
 * @author 李福春
 * 创建时间： 2020年02月26日 3:29 下午
 **/
@Service
public class SaasUserApiImpl implements ISaasUserApi {

    /**
     * 健康状态检查
     * @param name 检查的模块
     * @return 检查结果
     */
    @Override
    public String echo(String name) {
        return "echo success :".concat(name);
    }


}
