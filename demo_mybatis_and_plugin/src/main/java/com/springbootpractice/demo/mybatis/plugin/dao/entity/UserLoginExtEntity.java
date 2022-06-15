package com.springbootpractice.demo.mybatis.plugin.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: carter
* 对应数据库表： user_login_ext
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginExtEntity {
    public static final Integer IS_DELETED = Deleted.IS_DELETED.value();

    public static final Integer NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
    * 主键  对应数据库表字段: user_login_ext.id
    */
    private Integer id;

    /**
    * 用户名  对应数据库表字段: user_login_ext.user_name
    */
    private String userName;

    /**
    * 密码  对应数据库表字段: user_login_ext.password
    */
    private String password;

    /**
    * 性别[male(1):男, female(2):女]  对应数据库表字段: user_login_ext.sex
    */
    private Byte sex;

    /**
    * 备注  对应数据库表字段: user_login_ext.note
    */
    private String note;

    /**
    * 创建时间  对应数据库表字段: user_login_ext.created
    */
    private LocalDateTime created;

    /**
    * 更新时间  对应数据库表字段: user_login_ext.updated
    */
    private LocalDateTime updated;

    /**
    * 伪删除标记0正常1删除  对应数据库表字段: user_login_ext.deleted
    */
    private Integer deleted;

    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    public enum Deleted {
        NOT_DELETED(new Integer("0"), "未删除"),
        IS_DELETED(new Integer("1"), "已删除");

        private final Integer value;

        private final String name;

        Deleted(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return this.value;
        }

        public Integer value() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum Sex {
        MALE(new Byte("1"), "男"),
        FEMALE(new Byte("2"), "女");

        private final Byte value;

        private final String name;

        Sex(Byte value, String name) {
            this.value = value;
            this.name = name;
        }

        public Byte getValue() {
            return this.value;
        }

        public Byte value() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }
}