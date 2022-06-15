package com.carterbrother.springbootpractice.chapter3_demo.web;

import com.carterbrother.springbootpractice.chapter3_demo.scope.ScopeBean;
import com.carterbrother.springbootpractice.chapter3_demo.scope.ScopeBean3;
import com.carterbrother.springbootpractice.chapter3_demo.scope.ScopeBeanApplication;
import com.carterbrother.springbootpractice.chapter3_demo.scope.ScopeBeanSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 5:13 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class ScopeController {

    private static final Logger logger = LoggerFactory.getLogger(ScopeController.class);

    @GetMapping("/hello")
    public String hello(@Autowired ScopeBean3 scopeBean3, @Autowired ScopeBeanSession scopeBeanSession , @Autowired ScopeBeanApplication scopeBeanApplication)
    {
        logger.info("scopeBean3 :  {}",scopeBean3);
        logger.info("scopeBeanSe :  {}",scopeBeanSession);
        logger.info("scopeBeanApp :  {}",scopeBeanApplication);

        return String.format("hello:%s , %s , %s ",scopeBean3, scopeBeanSession, scopeBeanApplication);
    }
}
