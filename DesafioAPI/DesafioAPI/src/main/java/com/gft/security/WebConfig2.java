package com.gft.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig2 {

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
		.httpBasic()
		.and()
		.authorizeHttpRequests()
		.antMatchers("/v1/usuario").hasAuthority("admin")
		.antMatchers("/v1/etiqueta").hasAuthority("user")
		.antMatchers("/v1/perfil").hasAuthority("admin")
		.antMatchers("/v1/historico").hasAnyAuthority("admin", "user")
		.anyRequest().authenticated()
		.and()
		.csrf().disable();
		
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	            .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/swagger-ui/**");

	}
}
