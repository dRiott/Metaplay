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
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.formLogin().loginPage("/account/login").loginProcessingUrl("/account/login").defaultSuccessUrl("/account/profile")
				.and().rememberMe().key("metaplayKey")
				.and().logout().logoutSuccessUrl("/").logoutUrl("/logout")
				.and().authorizeRequests()
					.antMatchers("/account/login", "/account/review", "/account/save", "/account/add", "/", "/browse/**", "/more/payment", "/more/image").permitAll()
					.antMatchers("/role/add", "/role/assign").hasRole("GOD")
					.antMatchers("/artist/**", "/album/**", "/location/**", "/playlist/**", "/role/**", "/track/**", "/account/**").authenticated()
					.anyRequest().permitAll()
				.and().exceptionHandling().accessDeniedPage("/account/accessDenied");
		//for https
		//.and().requiresChannel().antMatchers("/account/save", "/album/save", "/artist/save", "/location/save", "/playlist/save", "/track/save").requiresSecure();
	}
}
