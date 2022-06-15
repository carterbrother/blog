package com.springbootpractice.demo.mybatis.plugin.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: carter
* 对应数据库表： saas_member
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaasMemberEntity {
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
    * 主键  对应数据库表字段: saas_member.id
    */
    private Integer id;

    /**
    * saas平台用户id  对应数据库表字段: saas_member.saas_user_id
    */
    private Integer saasUserId;

    /**
    * 商户代号appId,冗余  对应数据库表字段: saas_member.app_id
    */
    private String appId;

    /**
    * 商户的客户ID，冗余  对应数据库表字段: saas_member.merchant_user_id
    */
    private String merchantUserId;

    /**
    * 成为会员描述  对应数据库表字段: saas_member.become_desc
    */
    private String becomeDesc;

    /**
    * 截止有效期  对应数据库表字段: saas_member.expire_at
    */
    private LocalDateTime expireAt;

    /**
    * 创建时间  对应数据库表字段: saas_member.created
    */
    private LocalDateTime created;

    /**
    * 修改时间  对应数据库表字段: saas_member.updated
    */
    private LocalDateTime updated;

    /**
    * 0正常1删除  对应数据库表字段: saas_member.deleted
    */
    private Boolean deleted;

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