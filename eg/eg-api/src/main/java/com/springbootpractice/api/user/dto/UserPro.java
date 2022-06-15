package com.springbootpractice.api.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 用户的介绍信息
 * @date 2019年06月20日 16:30
 * @Copyright (c) carterbrother
 */
@Data
public class UserPro implements Serializable {

    /**
     * 用户ID
     */
    private Long id;
    /**
     * 姓名
     */
    private String userName;

}
