/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.config;

import com.ateamforce.coffeenow.service.impl.UserDetailsServiceImplementation;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *
 * @author alexa
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @Autowired
    private UserDetailsServiceImplementation userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        System.out.println(userDetailsService);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        public App1ConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("admin")
                    .and()
                    .formLogin()
                    .loginPage("/adminlogin")
                    .loginProcessingUrl("/admin_login")
                    .failureUrl("/adminlogin?error=loginError")
                    .defaultSuccessUrl("/adminPage")
                    .and()
                    .logout()
                    .logoutUrl("/store/dashboard")
                    .logoutSuccessUrl("/logout")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")
                    .and()
                    .csrf().disable();
        }

    }

    @Configuration
    @Order(2)
    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        public App2ConfigurationAdapter() {
            super();
        }

        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/store/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("store")
                    .and()
                    .formLogin()
                    .loginPage("/storelogin")
                    .loginProcessingUrl("/store_login")
                    .failureUrl("/storelogin?error=loginError")
                    .defaultSuccessUrl("/store/dashboard")
                    .and()
                    .logout()
                    .logoutUrl("/storelogout")
                    .logoutSuccessUrl("/logout")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")
                    .and()
                    .csrf().disable();
        }
    }
}
