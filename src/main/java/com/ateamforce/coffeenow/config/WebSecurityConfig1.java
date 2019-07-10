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
public class WebSecurityConfig1 extends WebSecurityConfigurerAdapter { 
 
     
 
        @Autowired 
        private UserDetailsServiceImplementation userDetailsService; 
 
        @Autowired 
        private DataSource dataSource; 
 
        @Bean 
        public BCryptPasswordEncoder passwordEncoder() { 
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 
            return bCryptPasswordEncoder; 
        } 
 
        @Override 
        public void configure(AuthenticationManagerBuilder auth) throws Exception { 
 
            // Setting Service to find User in the database. 
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); 

        } 
 
        @Override 
        protected void configure(HttpSecurity http) throws Exception {
            
            http.csrf().disable() 
                        .authorizeRequests().antMatchers("/", "/admin","/store").permitAll(); 
 
            http.authorizeRequests().and() // 
                    .rememberMe().tokenRepository(this.persistentTokenRepository()) // 
                    .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h 
        }
 
        @Bean 
        public PersistentTokenRepository persistentTokenRepository() { 
            InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); 
            return memory; 
        } 
        
        @Configuration
        @Order(1)
        public static class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter{
            
            @Override 
            protected void configure(HttpSecurity http) throws Exception { 

                http.authorizeRequests().antMatchers("/admin/**").hasAuthority("admin").and().formLogin()
                        .loginProcessingUrl("/admin")
                        .loginPage("/admin")
                        .defaultSuccessUrl("/admin/dashboard")
                        .failureUrl("/admin?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password"); 

            }
            
        }
        
        @Configuration
        @Order(2)
        public static class StoreWebSecurityConfig extends WebSecurityConfigurerAdapter{
            
            @Override 
            protected void configure(HttpSecurity http) throws Exception { 

                http.authorizeRequests().antMatchers("/store/**").hasAuthority("client").and().formLogin()
                        .loginProcessingUrl("/store")
                        .loginPage("/store")
                        .defaultSuccessUrl("/store/dashboard")
                        .failureUrl("/store?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password"); 
            }
            
        }
 
    } 