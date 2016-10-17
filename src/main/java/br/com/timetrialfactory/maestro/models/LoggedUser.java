package br.com.timetrialfactory.maestro.models;

import static br.com.timetrialfactory.maestro.models.enums.RoleType.ADMIN;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.LicenseDTO;

@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class LoggedUser {

	public LoggedUser() {

	}

	public LoggedUser(LoggedUser user) {

	}

	private User logged;

	private Set<LicenseDTO> licenses;

	public void login(User user) {
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
		return logged != null && logged.getRole().equals(ADMIN);
	}

	public void logout() {
		this.logged = null;
	}

	public Set<LicenseDTO> getLicenses() {
		return licenses;
	}

	public void setLicenses(Set<LicenseDTO> licenses) {
		this.licenses = licenses;
	}

	public boolean isHasGames() {
		return !this.licenses.isEmpty();
	}

}
