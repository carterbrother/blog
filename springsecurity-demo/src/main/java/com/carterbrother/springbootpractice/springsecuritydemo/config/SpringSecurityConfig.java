package com.carterbrother.springbootpractice.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 配置用户服务信息
 * @date 2019年04月10日 11:02 AM
 * @Copyright (c) carterbrother
 */
@Component
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        PasswordEncoder myPasswordEncoder = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .passwordEncoder(myPasswordEncoder)
//                .withUser("admin").password(myPasswordEncoder.encode("123456789")).roles("USER","ADMIN")
//                .and()
//                .withUser("user").password("$2a$10$XQCn28lYBO.pzfc3qj.KWuF4Lk1Ojv.JDSL.ijc5VlmzG5lMEKTR2").roles("USER");
//
//    }

//    @Autowired
//    private DataSource dataSource;
//    private String pwdQuery = "select user_name,psw,available from t_user where user_name = ?";
//    private String roleQuery = "select u.user_name, r.role_name from t_user u , t_user_role ru, t_role r  where u.id=ru.user_id and r.id=ru.role_id  and u.user_name = ? ";
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        PasswordEncoder myPasswordEncoder = new BCryptPasswordEncoder();
//
//        auth.jdbcAuthentication().passwordEncoder(myPasswordEncoder)
//        .dataSource(dataSource)
//        .usersByUsernameQuery(pwdQuery)
//        .authoritiesByUsernameQuery(roleQuery);
//    }
    @Autowired
    private UserDetailBean userDetailBean;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder myPasswordEncoder = new BCryptPasswordEncoder();

        auth.userDetailsService(userDetailBean).passwordEncoder(myPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //任何请求都需要授权，使用基本的登录页，启用基本的http认证
//        http.authorizeRequests().anyRequest().authenticated()
//                .and().formLogin()
//                .and().httpBasic();
//
//        http.authorizeRequests()
//                .regexMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
//                .regexMatchers("/admin/.*").hasAnyAuthority("ROLE_ADMIN")
//                .and().formLogin()
//                .and().httpBasic();


//        http.authorizeRequests()
//                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
//                .regexMatchers("/admin/.*").access("hasAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
//                .and().rememberMe()
//                .and().formLogin()
//                .and().httpBasic();
//
//
//
//        http.requiresChannel().antMatchers("/user/**").requiresInsecure()
//                .and().requiresChannel().regexMatchers("/admin/.*").requiresSecure()
//                .and().authorizeRequests()
//                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
//                .regexMatchers("/admin/.*").access("hasAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
//                .and().rememberMe()
//                .and().formLogin()
//                .and().httpBasic();
//
//


        AuthenticationSuccessHandler myLoginSuccessHandler = new MyAuthenticationSuccessHandler();
        LogoutSuccessHandler myLogoutSuccessHandler = new MyLogoutSuccessHandler();
        http.requiresChannel().antMatchers("/user/**").requiresInsecure()
                .and().requiresChannel().regexMatchers("/admin/.*").requiresSecure()
                .and().authorizeRequests()
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .regexMatchers("/admin/.*").access("hasAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
                .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
                .and().formLogin().successHandler(myLoginSuccessHandler).defaultSuccessUrl("/user/welcome",true)/*.loginPage("/login").defaultSuccessUrl("/user/welcome")*/
                .and().httpBasic().realmName("xx")
        .and().logout().logoutSuccessHandler(myLogoutSuccessHandler).logoutSuccessUrl("/login");




    }
}
