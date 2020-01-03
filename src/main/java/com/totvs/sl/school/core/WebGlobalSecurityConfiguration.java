package com.totvs.sl.school.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebGlobalSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		   .antMatchers(HttpMethod.OPTIONS)
		   .antMatchers("/v2/api-docs",
		                "/configuration/ui",
		                "/swagger-resources",
		                "/configuration/security",
		                "/api/swagger-ui.html",
		                "/webjars/**");

	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf()
		            .disable()
		            .cors()
		            .and()
		            .authorizeRequests()
		            .antMatchers("/api/**")
		            .permitAll()
		            .anyRequest()
		            .authenticated();

	}

}
