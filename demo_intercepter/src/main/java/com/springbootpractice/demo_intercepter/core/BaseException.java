package com.springbootpractice.demo_intercepter.core;

import java.io.Serializable;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 自定义的父异常
 * @date 2019年05月17日 5:43 PM
 * @Copyright (c) carterbrother
 */
public class BaseException extends RuntimeException implements Serializable {
    private static final int DEFAULT_ERROR_CODE = 500;
    /**
     * 错误码
     */
    private Integer code ;
    /**
     * 错误消息
     */
    private String  msg;


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public BaseException(Integer code, String msg) {
        this(code,msg,null);
    }


    public BaseException(String message) {
        this(DEFAULT_ERROR_CODE,message);

    }

    public BaseException(Integer code,String msg,Object throwable)
    {
        super(msg,(Throwable) throwable );
        this.code = code;
        this.msg = msg;
    }

}
