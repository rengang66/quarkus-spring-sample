package com.iiit.spring.sample.security.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		  http.authorizeRequests() .antMatchers("/admin").hasRole("ADMIN")
		  .antMatchers("/user").hasAnyRole("USER","ADMIN")
		  .anyRequest().authenticated().and().csrf().disable().formLogin();
		  http.headers().frameOptions().disable();		
		 */
		  
		  http.authorizeRequests() 
		    .antMatchers("/oauth2/admin").hasRole("ADMIN")
		    .antMatchers("/oauth2/user").hasAnyRole("USER","ADMIN")
		   // .antMatchers("/annonymous","/oauth/token","/login","/oauth2/**").permitAll()
		    .antMatchers("/annonymous","/oauth/token","/login").permitAll()
		    .anyRequest().authenticated().and().csrf().disable().formLogin();
		  http.headers().frameOptions().disable();	
		  
		  /*
		  http.headers().frameOptions().disable().and()
          .authorizeRequests()
          .antMatchers("/","/home","/oauth/token","/login").permitAll()
          .antMatchers("/private/**").authenticated();
		  */
		  
		  
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
