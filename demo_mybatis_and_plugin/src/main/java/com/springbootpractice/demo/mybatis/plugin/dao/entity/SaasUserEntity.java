package com.springbootpractice.demo.mybatis.plugin.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: carter
* 对应数据库表： saas_user
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaasUserEntity {
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
    */
    private Integer id;

    /**
    * saas用户ID  对应数据库表字段: saas_user.login_id
    */
    private String loginId;

    /**
    * 商户代号appId  对应数据库表字段: saas_user.app_id
    */
    private String appId;

    /**
    * 商户的客户ID  对应数据库表字段: saas_user.mechant_user_id
    */
    private String mechantUserId;

    /**
    * 商户的客户名称  对应数据库表字段: saas_user.login_name
    */
    private String loginName;

    /**
    * 令牌  对应数据库表字段: saas_user.access_token
    */
    private String accessToken;

    /**
    * 令牌有效期  对应数据库表字段: saas_user.expire_at
    */
    private Long expireAt;

    /**
    * 创建时间  对应数据库表字段: saas_user.created
    */
    private LocalDateTime created;

    /**
    * 修改时间  对应数据库表字段: saas_user.updated
    */
    private LocalDateTime updated;

    /**
    * 0正常1删除  对应数据库表字段: saas_user.deleted
    */
    private Boolean deleted;

    /**
    * 用户头像  对应数据库表字段: saas_user.head_url
    */
    private String headUrl;

    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        private final Boolean value;

        private final String name;

        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        public Boolean getValue() {
            return this.value;
        }

        public Boolean value() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }
}