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
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    Securityhandler successHandler;

    @Autowired
    private AppUserService appUserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(appUserService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/img/**", "/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
                .sessionAuthenticationErrorUrl("/administrator?sessionExpired=true")
                .invalidSessionUrl("/administrator?sessionExpired=true");

        http
                .antMatcher("/administrator/dashboard/**")
                .authorizeRequests()
                .anyRequest()
                .hasAuthority("admin")
                .and()
                .formLogin()
                .loginPage("/administrator")
                .loginProcessingUrl("/administrator/dashboard/check")
                .failureUrl("/administrator?error=true")
                .successHandler(successHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/administrator/dashboard/logout")
                .logoutSuccessUrl("/administrator?logout=true")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/administrator?accessDenied=true")
                .and()
                .csrf().disable();

    }

    @Configuration
    @Order(1)
    public class ExtraSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity web) throws Exception {
            // TODO: spring security sets no-cache headers for static resources, and this code is supposed to fix that, but it's not working.
            // check https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#headers
            // and https://stackoverflow.com/questions/24164014/how-to-enable-http-response-caching-in-spring-boot
            web.ignoring().antMatchers("/img/**", "/css/**", "/js/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.sessionManagement()
                    .sessionAuthenticationErrorUrl("/store?sessionExpired=true")
                    .invalidSessionUrl("/store?sessionExpired=true");

            http
                    .antMatcher("/store/dashboard/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasAuthority("store")
                    .and()
                    .formLogin()
                    .loginPage("/store")
                    .loginProcessingUrl("/store/dashboard/check")
                    .failureUrl("/store?error=true")
                    .successHandler(successHandler)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                    .logout()
                    .logoutUrl("/store/dashboard/logout")
                    .logoutSuccessUrl("/store?logout=true")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/store?accessDenied=true")
                    .and()
                    .csrf().disable();

        }

    }

}
