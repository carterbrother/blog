package com.authine.cloudpivot.app.flyway.domain.exception;

import com.alibaba.cola.dto.ErrorCodeI;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author carter
 * create_date  2020/7/7 13:55
 * description     应用级别的错误码统一定义
 */

@AllArgsConstructor
public enum DemoErrorCodeEnum implements ErrorCodeI {

    PARAM_ERR("11010000","参数检查出错","请按照输入提示输入或者选择"),
    BIZ_CREATE_GATEWAY_ERR("11020001","创建网关出错","请检查应用的编码最好只包含字母和数字"),
    BIZ_CREATE_CONFIG_ERR("11020002","创建配置文件出错","请联系运维人员检查应用的配置文件模板在nacos中是否存在"),
    SYS_DATABASE_LINK_ERR("11030001","数据库出错","请联系运维人员检查数据库的连接信息"),
    SYS_NPE_ERR("11030002","空指针错误","请联系开发人员解决问题"),
    ;

    private String errorCode;

    private String errorDesc;

    @Getter
    private String correctGuid;




    @Override
    public String getErrCode() {
        return errorCode;
    }

    @Override
    public String getErrDesc() {
        return errorDesc;
    }

    @Override
    public String getServiceName() {
        return "demo";
    }


}
