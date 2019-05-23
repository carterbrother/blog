package com.springx.bootdubbo.starter.rest.core;

import com.springx.bootdubbo.common.bean.LoginSessionBean;
import com.springx.bootdubbo.common.enums.AppTypeEnum;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 校验登录，检查功能权限，获取数据权限接口
 * @date 2019年05月23日 9:32 AM
 * @Copyright (c) carterbrother
 */
public interface LoginCheckApi {
    /**
     * 通过appType和accessToken获取登录的会话信息
     *
     * @param appTypeEnum 应用枚举
     * @param accessToken 令牌
     * @return
     */
    LoginSessionBean getLoginSessionBean(AppTypeEnum appTypeEnum, String accessToken);

    /**
     * 检查用户在某系统是否具备powerKey对应的功能权限
     *
     * @param appTypeEnum 应用枚举
     * @param loginId     登录用户ID
     * @param powerKey    功能权限Key
     * @return
     */
    Boolean hasPower(AppTypeEnum appTypeEnum, Long loginId, String powerKey);

    /**
     * 查询用户在某个系统对应的功能的数据权限转化出来的sql
     *
     * @param appTypeEnum
     * @param loginId
     * @param powerKey
     * @return
     */
    String getDataPowerSql(AppTypeEnum appTypeEnum, Long loginId, String powerKey);


}
