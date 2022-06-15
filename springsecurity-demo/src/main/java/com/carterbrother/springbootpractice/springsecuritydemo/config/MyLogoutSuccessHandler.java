package com.carterbrother.springbootpractice.springsecuritydemo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年04月10日 4:59 PM
 * @Copyright (c) carterbrother
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = Logger.getAnonymousLogger();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("logout success ==> "+ authentication.getPrincipal());
    }
}
