package com.spring.story.demo.cache.pre.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class RedissonProperties {


    /**
     * redis主机地址，ip：port，有多个用半角逗号分隔
     */
    private String address;

    /**
     * redis 连接密码
     */
    private String password;

    /**
     * 选取那个数据库
     */
    private int database;

    /**
     * 客户端的名称
     */
    private String clientName;

    /**
     * 连接池大小
     */
    private Integer poolSize;

}