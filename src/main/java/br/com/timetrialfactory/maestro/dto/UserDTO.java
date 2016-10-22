package br.com.timetrialfactory.maestro.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.timetrialfactory.maestro.models.embeddables.Address;

public class UserDTO {

	@NotNull
	private Long id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String login;

	@NotNull
	private String password;

	@NotNull
	private String email;

	@Valid
	private Address address;

	@NotNull
	private String role;

	private boolean active;

	@NotNull
	private Long activationCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(Long activationCode) {
		this.activationCode = activationCode;
	}

}
