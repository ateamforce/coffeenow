/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */
package com.ateamforce.coffeenow.config;

import com.ateamforce.coffeenow.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author alexa
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService appUserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/administrator/*")
                .hasAuthority("admin")
                .and()
                .formLogin()
                .loginPage("/administrator")
                .loginProcessingUrl("/administrator")
                .failureUrl("/administrator?error=true")
                .defaultSuccessUrl("/administrator/dashboard")
                .usernameParameter("userId")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/administrator/logout")
                .logoutSuccessUrl("/administrator")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .csrf().disable();

    }

    @Configuration
    @Order(1)
    public static class ExtraSecurityConfig extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {

            http
                    .antMatcher("/store/*")
                    .authorizeRequests()
                    .anyRequest()
                    .hasAuthority("client")
                    .and()
                    .formLogin()
                    .loginPage("/store")
                    .loginProcessingUrl("/store")
                    .failureUrl("/store?error=true")
                    .defaultSuccessUrl("/store/dashboard")
                    .usernameParameter("userId")
                    .passwordParameter("password")
                    .and()
                    .logout()
                    .logoutUrl("/store/logout")
                    .logoutSuccessUrl("/store")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")
                    .and()
                    .csrf().disable();

        }

    }

}
