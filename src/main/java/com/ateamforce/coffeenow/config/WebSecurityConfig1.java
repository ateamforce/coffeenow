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
            System.out.println(userDetailsService); 
        } 
 
        @Override 
        protected void configure(HttpSecurity http) throws Exception { 
 
            http 
                    .csrf().disable() 
                    .authorizeRequests().antMatchers("/", "/admin", "back_admin/login").permitAll(); 
 
            http.authorizeRequests().antMatchers("back_admin/dashboard/**").hasAuthority("admin"); 
 
            //http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403"); 
            http.authorizeRequests().and().formLogin()// 
                    // Submit URL of login page. 
                    .loginProcessingUrl("/admin_login") // Submit URL 
                    .loginPage("/back_admin/login")// 
                    .defaultSuccessUrl("/admin/dashboard")// 
                    .failureUrl("/loginform?error=true")// 
                    .usernameParameter("username")// 
                    .passwordParameter("password"); 
            // Config for Logout Page 
            //.and().logout().logoutUrl("/logout").logoutSuccessUrl("/home"); 
 
            http.authorizeRequests().and() // 
                    .rememberMe().tokenRepository(this.persistentTokenRepository()) // 
                    .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h 
        } 
 
        @Bean 
        public PersistentTokenRepository persistentTokenRepository() { 
            InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); 
            return memory; 
        } 
 
    } 