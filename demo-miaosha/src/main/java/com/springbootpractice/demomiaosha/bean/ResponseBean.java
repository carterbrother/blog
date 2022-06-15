package com.springbootpractice.demomiaosha.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年06月25日 17:26
 * @Copyright (c) carterbrother
 */
@Data
@ToString
public class ResponseBean  implements Serializable {
    /**
     * 返回结果，true 成功 false 失败
     */
    private Boolean success;
    /**
     * 消息
     */
    private String   message;
    /**
     * 数据
     */
    private Object   data;
}
