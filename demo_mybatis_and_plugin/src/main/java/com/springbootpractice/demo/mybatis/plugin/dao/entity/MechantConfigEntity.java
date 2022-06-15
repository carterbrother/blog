package com.springbootpractice.demo.mybatis.plugin.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: carter
* 对应数据库表： mechant_config
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MechantConfigEntity {
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
    */
    private Integer id;

    /**
    * 商户代号appId  对应数据库表字段: mechant_config.app_id
    */
    private String appId;

    /**
    * 商户的秘钥  对应数据库表字段: mechant_config.app_secret
    */
    private String appSecret;

    /**
    * 商户名称  对应数据库表字段: mechant_config.mechant_name
    */
    private String mechantName;

    /**
    * 合作结束时间  对应数据库表字段: mechant_config.cooperate_endtime
    */
    private LocalDateTime cooperateEndtime;

    /**
    * 备注  对应数据库表字段: mechant_config.memo
    */
    private String memo;

    /**
    * 创建时间  对应数据库表字段: mechant_config.created
    */
    private LocalDateTime created;

    /**
    * 修改时间  对应数据库表字段: mechant_config.updated
    */
    private LocalDateTime updated;

    /**
    * 0正常1删除  对应数据库表字段: mechant_config.deleted
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