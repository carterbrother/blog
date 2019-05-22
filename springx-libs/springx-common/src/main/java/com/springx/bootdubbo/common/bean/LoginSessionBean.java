package com.springx.bootdubbo.common.bean;

import lombok.Builder;

import java.io.Serializable;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 登录会话信息
 * @date 2019年05月22日 2:03 PM
 * @Copyright (c) carterbrother
 */
@Builder
public class LoginSessionBean implements Serializable {
    private static final long serialVersionUID = -1553151909537841505L;
    /**
     * 登录的用户ID字符串
     */
    private String loginId;
    /**
     * 登录用户的真实姓名,加密
     */
    private String loginRealName;
    /**
     * 产品ID
     */
    private Integer appType;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 令牌
     */
    private String accessToken;
    /**
     * 令牌过期时间
     */
    private Long expireAt;
    /**
     * 是否是灰度环境
     */
    private Boolean gray;
    /**
     * 签名，防止内容被篡改
     */
    private String sign;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginRealName() {
        return loginRealName;
    }

    public void setLoginRealName(String loginRealName) {
        this.loginRealName = loginRealName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }

    public Boolean getGray() {
        return gray;
    }

    public void setGray(Boolean gray) {
        this.gray = gray;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
