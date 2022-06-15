package com.springbootpractice.demo.spring.security1.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 说明：springSecurity配置
 * @author carter
 * 创建时间： 2020年02月07日 11:57 下午
 **/
@EnableWebSecurity(debug = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyPasswordEncoder myPasswordEncoder;
    private final MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    public SecurityConfig(MyPasswordEncoder myPasswordEncoder, MyUserDetailsServiceImpl myUserDetailsServiceImpl) {
        this.myPasswordEncoder = myPasswordEncoder;
        this.myUserDetailsServiceImpl = myUserDetailsServiceImpl;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置密码校验规则，用户和角色对应关系
        auth.userDetailsService(myUserDetailsServiceImpl)
                .passwordEncoder(myPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/**").authenticated()
                .and().formLogin()
                .and().httpBasic().realmName("admin login");
//                .and().authorizeRequests().antMatchers("/**").permitAll()
//                .and().formLogin().loginPage("/login/page").successForwardUrl("/home/page").successHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                log.info("登录成功！！！");
//            }
//        });
//                http.httpBasic().realmName("my-basic-name");
    }
}
