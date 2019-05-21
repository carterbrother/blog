package com.springx.bootdubbo.common.bean;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年05月21日 7:22 PM
 * @Copyright (c) carterbrother
 */
public enum ErrorCodeMsgEnum implements BaseEnum<Integer> {
    /**
     * 成功消息
     */
    SUCCESS(200, "success"),
    /**
     * 出错消息
     */
    ERROR(500, "error"),
    /**
     * 找不到资源
     */
    NOT_FOUND(404, "not found"),
    /**
     * 格式错误
     */
    PARAM_FORMAT_ERROR(400, "param formate error"),
    ;

    private Integer code;
    private String msg;

    ErrorCodeMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 编码
     *
     * @return
     */
    @Override
    public Integer code() {
        return code;
    }

    /**
     * 获取错误信息
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 通过code找到枚举
     *
     * @param code 错误编码
     * @return
     */
    public static ErrorCodeMsgEnum fromCode(Integer code) {
        return BaseEnum.parse(ErrorCodeMsgEnum.class, code);
    }

}
