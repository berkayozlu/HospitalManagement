package com.hospital;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder =
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth
				.inMemoryAuthentication()
				.withUser("patient")
				.password(encoder.encode("123"))
				.roles("USER","PATIENT")
				.and()
				.withUser("sarah")
				.password(encoder.encode("123"))
				.roles("USER", "RECEPTIONIST")
				.and()
				.withUser("tom")
				.password(encoder.encode("123"))
				.roles("USER","DOCTOR");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/main").permitAll()
				.antMatchers("/doctors").hasRole("DOCTOR")
				.antMatchers("/patients/**").hasRole("PATIENT")
				.antMatchers("/receptionist").hasRole("RECEPTIONIST")
				.antMatchers("/login*").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}

}
