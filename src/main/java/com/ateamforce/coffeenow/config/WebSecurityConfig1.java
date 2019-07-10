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
public class WebSecurityConfig1 extends WebSecurityConfigurerAdapter {
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("john").password("pa55word").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		// redirect users to the login page if authentication is required, and set
		// parameters
		httpSecurity.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password");

		// The request parameters (after ?) we are setting here, should match the
		// parameter names that we are checking in the login.jsp
		httpSecurity.formLogin().defaultSuccessUrl("/market/products/add").failureUrl("/login?error");
		httpSecurity.logout().logoutSuccessUrl("/login?logout");
		httpSecurity.exceptionHandling().accessDeniedPage("/login?accessDenied");

		httpSecurity.authorizeRequests().antMatchers("/").permitAll().antMatchers("/**/add").access("hasRole('ADMIN')")
				.antMatchers("/**/market/**").access("hasRole('USER')");
		httpSecurity.csrf().disable();
	}
}

