package br.com.springmvc.timetrialfactory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("adm").password("123456").roles("ADMIN");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/games/form").hasRole("ADMIN").antMatchers("/css/**").permitAll()
				.antMatchers("/shopping/**").permitAll().antMatchers(HttpMethod.POST, "/games").hasRole("ADMIN")
				.antMatchers("/games/**").permitAll().antMatchers("/**").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("login")
				.passwordParameter("password").and().csrf().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

}
