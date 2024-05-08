package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


/**
 * @author :panligang
 * @description :
 * @create :2024-04-02 16:57:00
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;


    @Autowired
    AuthenticationProvider authenticationProvider;

    /**
     * 配置认证方式
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置认证方式
        // 很灵活的一种方式 用户实现loadUserByUsername方法
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
        auth.authenticationProvider(authenticationProvider);

        // 不论是基于内存还是数据库最终都是一个UserDetailsService的实现类

        /**
         * JdbcUserDetailsManager
         * InMemoryUserDetailsManager
         * 都实现了UserDetailsService
         */
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       http.requestMatchers().antMatchers("/oauth/**")
//               .and().authorizeRequests()
//               .antMatchers("/oauth/**")
//               .authenticated()
//               .and().csrf().disable().formLogin();
//    }

    // 给spring容器中注入认证管理器,后续配置认证管理器的时候会使用到
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }




}
