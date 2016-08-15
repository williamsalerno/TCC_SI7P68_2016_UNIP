package br.com.springmvc.timetrialfactory.models;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.springmvc.timetrialfactory.dto.UserDTO;
import br.com.springmvc.timetrialfactory.models.enums.RoleType;

@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class LoggedUser {

	private UserDTO logged;

	public void login(UserDTO user) {
		this.logged = user;
	}

	public User getLoggedUser() {
		return logged;
	}

	public String getName() {
		return logged.getFirstName();
	}

	public Long getId() {
		return logged.getId();
	}

	public boolean isLogged() {
		return logged != null;
	}
	
	public boolean isAdmin() {
		return logged != null && logged.getRole().equals(RoleType.ADMIN);
	}

	public void logout() {
		this.logged = null;
	}

	@Override
	public String toString() {
		return "LoggedUser [logged=" + logged.toString() + "]";
	}

}
