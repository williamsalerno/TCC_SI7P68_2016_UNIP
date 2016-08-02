package br.com.springmvc.timetrialfactory.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/games/form").hasRole("ADMIN").antMatchers("/css/**").permitAll()
				.antMatchers("/shopping/**").permitAll().antMatchers(HttpMethod.POST, "/games").hasRole("ADMIN")
				.antMatchers("/games/**").permitAll().antMatchers("/**").permitAll().anyRequest().authenticated().and()
				.csrf().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

}
