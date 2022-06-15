package com.springbootpractice.demostatemachine.state;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年04月15日 11:42 AM
 * @Copyright (c) carterbrother
 */
public enum OrderStateEnum {

    start,
    waitPay,
    payed,
    signed,
    tuihuoying,
    tuihuoSuccess,
    tuikuaning,
    tuikuanSuccess,
    end

}
