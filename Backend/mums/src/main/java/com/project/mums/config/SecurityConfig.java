package com.project.mums.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.mums.security.JwtAuthenticationEntryPoint;
import com.project.mums.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, jsr250Enabled = true)
public class SecurityConfig {
	
	@Bean
	UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean 
	public SecurityFilterChain securityFilterChainHttpSecurity (HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.cors().and()
		.authorizeHttpRequests()
		.antMatchers("/login","/upload/**").permitAll()
		.antMatchers("/employeeAudit","/incmexptally", "/production").hasRole("ADMIN")
		.antMatchers("/oldsalary","/employee").hasAnyRole("EMPLOYEE", "ADMIN")
		.anyRequest()
		.authenticated().and()
		.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.authenticationProvider(daoAuthenticationProvider());
		DefaultSecurityFilterChain build = http.build();
		return build;
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.getUserDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration)  throws Exception {
		return configuration.getAuthenticationManager();
	}
}