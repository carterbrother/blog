package com.springx.bootdubbo.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description Rest统一返回给前端的数据对象
 * @date 2019年05月17日 5:31 PM
 * @Copyright (c) carterbrother
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponseBean implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private Object data;

    @Override
    public String toString() {
        return new StringJoiner(", ", RestResponseBean.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .add("data=" + data)
                .toString();
    }

}
