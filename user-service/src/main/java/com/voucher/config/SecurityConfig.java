package com.voucher.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.voucher.jwt.EntryPointJwt;
import com.voucher.jwt.TokenFilter;
import com.voucher.securityservice.UserDetailsServiceImpl;

@Configuration

@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig {

 

	@Autowired
	EntryPointJwt entryPoint;

	@Autowired
	TokenFilter tokenFilter;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

 

	@Bean
	public DaoAuthenticationProvider authenticate() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}

 

	@Bean
	public SecurityFilterChain doFilter(HttpSecurity http) throws Exception {
		
		return http
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(entryPoint)
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/error","/v3/**","swagger-ui/**","/app/**","/user/register","/user/**")
				.permitAll()
				.requestMatchers("/requests/**","/voucher/**").authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
				.build();

		

	}

 

	@Bean
	public PasswordEncoder passwordEncoder() {

		return  new BCryptPasswordEncoder();

	}

}
