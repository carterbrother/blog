package com.springx.bootdubbo.common.param;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description Id参数，删除和查询多个用到
 * @date 2019年05月21日 6:09 PM
 * @Copyright (c) carterbrother
 */
public class IdParam implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLongValue() {
        if (StringUtils.isNumeric(id)) {
            return Long.parseLong(id);
        }
        return 0L;
    }

    public Integer getIntegerValue() {
        return getLongValue().intValue();
    }

    @Override
    public String toString() {
        return "IdParams [id=" + id + "]";
    }
}