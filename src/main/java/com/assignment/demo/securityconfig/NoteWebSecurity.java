package com.assignment.demo.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



/*
 * 	This class is used to authenticate the user. 
 * DaoAuthenticationProvider Bean  is an AuthenticationProvider implementation that leverages a 
 * UserDetailsService and PasswordEncoder to authenticate a username and password.
 * configure method is used to configure the api mapping, on role based authentication api allow,
 * default access for api.
 * */

@Configuration
@EnableWebSecurity
@Order
public class NoteWebSecurity extends WebSecurityConfigurerAdapter {
	
	

	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
	         
	        return authProvider;
	    }
	 @Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable().authorizeRequests().antMatchers("/").permitAll()
			.antMatchers("/note/api/**").hasAuthority("USER")
			.anyRequest().authenticated().and().httpBasic();
			
		}
	 
	 

}
