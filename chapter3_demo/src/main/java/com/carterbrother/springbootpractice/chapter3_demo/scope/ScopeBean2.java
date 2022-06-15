package com.carterbrother.springbootpractice.chapter3_demo.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 5:03 PM
 * @Copyright (c) carterbrother
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ScopeBean2 {
    private long id = System.currentTimeMillis();

    public long getId() {
        return id;
    }
}
