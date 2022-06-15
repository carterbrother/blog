package com.springbootpractice.demostatemachine.listener;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.util.logging.Logger;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年04月15日 11:46 AM
 * @Copyright (c) carterbrother
 */
@WithStateMachine
public class OrderProcessListener {

    private static final Logger logger = Logger.getLogger(OrderProcessListener.class.getName());

    @OnTransition(target = "waitPay")
    public void createOrder(){
        logger.info("创建订单");
    }


}
