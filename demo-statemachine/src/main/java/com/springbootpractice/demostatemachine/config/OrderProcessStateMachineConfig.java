package com.springbootpractice.demostatemachine.config;

import com.springbootpractice.demostatemachine.event.OrderProcessEvent;
import com.springbootpractice.demostatemachine.state.OrderStateEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年04月15日 11:43 AM
 * @Copyright (c) carterbrother
 */
@Configuration
@EnableStateMachine
public class OrderProcessStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStateEnum, OrderProcessEvent> {


    @Override
    public void configure(StateMachineStateConfigurer<OrderStateEnum, OrderProcessEvent> states) throws Exception {
        states.withStates().initial(OrderStateEnum.waitPay).states(EnumSet.allOf(OrderStateEnum.class));
    }


    @Override
    public void configure(StateMachineConfigBuilder<OrderStateEnum, OrderProcessEvent> config) throws Exception {
        super.configure(config);
    }
}
