package com.thoughtriott.metaplay.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MetaplaySecurityConfig extends WebSecurityConfigurerAdapter {
	// Spring MVC integration: http://docs.spring.io/spring-security/site/docs/current/reference/html/mvc.html
	
	@Autowired
	BasicDataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select accountname, password, enabled from account where accountname=?")
		.authoritiesByUsernameQuery("select account.accountname, role.Id from account, role where account.accountname=? ");
		//and account.id = account_role.account_id and account_role.role_id = role.id
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.formLogin().loginPage("/account/login")
		.and().logout().logoutSuccessUrl("/").logoutUrl("/logout")
		.and().authorizeRequests()
		.antMatchers("/artist/**", "/album/**", "/location/**", "/playlist/**", "/role/**", "/track/**").authenticated()
		.antMatchers("/role/add").hasAuthority("God")
		.anyRequest().permitAll();
//		.and().formLogin().loginPage("/account/login")
//		.and().httpBasic();
	}
}
