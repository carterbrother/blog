package com.zengame.platweb.client;

import lombok.Data;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/30  14:40
 **/
@Data
public class OomRequest {

    /**
     * 线程数量
     */
    private Integer threadNum;

    /**
     * 对象数量
     */
    private Integer ObjNum;

}
