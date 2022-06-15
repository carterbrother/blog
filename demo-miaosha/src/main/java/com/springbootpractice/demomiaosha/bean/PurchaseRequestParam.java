package com.springbootpractice.demomiaosha.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 购买的请求参数
 * @date 2019年06月25日 17:34
 * @Copyright (c) carterbrother
 */
@Data
public class PurchaseRequestParam implements Serializable {

    private Long userId;
    private Long productId;
    private Integer qty;
    private String  methodType="normal";

}
