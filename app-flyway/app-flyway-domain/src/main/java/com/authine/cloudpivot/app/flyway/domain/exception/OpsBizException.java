package com.authine.cloudpivot.app.flyway.domain.exception;

import com.alibaba.cola.dto.ErrorCodeI;
import com.alibaba.cola.exception.BizException;

/**
 * @author carter
 * create_date  2020/7/7 14:14
 * description     运维操作的业务异常定义
 */

public class OpsBizException extends BizException {

    /**
     * 只能传入本应用定义好的ErrorCodeI实例
     * @param errorCodeI 应用级别的错误码
     * @param e 异常
     */
    public OpsBizException(ErrorCodeI errorCodeI, Throwable e) {
        super(errorCodeI.getErrDesc(), e);
        super.setErrCode(errorCodeI);
    }
}
