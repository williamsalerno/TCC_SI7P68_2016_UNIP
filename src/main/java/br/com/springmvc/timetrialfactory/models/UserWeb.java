package br.com.springmvc.timetrialfactory.models;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class UserWeb {
	
	public User getLoggedUser() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
