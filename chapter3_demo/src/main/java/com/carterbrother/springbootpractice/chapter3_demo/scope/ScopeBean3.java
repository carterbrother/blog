package com.carterbrother.springbootpractice.chapter3_demo.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 5:03 PM
 * @Copyright (c) carterbrother
 */
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ScopeBean3 {
    private long id = System.currentTimeMillis();

    public long getId() {
        return id;
    }
}
