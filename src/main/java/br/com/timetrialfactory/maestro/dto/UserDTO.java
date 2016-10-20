package br.com.timetrialfactory.maestro.dto;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.com.timetrialfactory.maestro.models.embeddables.Address;

public class UserDTO {

	private static final int MIN_LOGIN_LENGTH = 3;
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final int MAX_LENGTH = 12;

	@Null
	private Long id;

	@NotBlank
	@Pattern(regexp = "\\p{L}+")
	private String firstName;

	@NotBlank
	@Pattern(regexp = "\\p{L}+")
	private String lastName;

	@NotBlank
	@Size(min = MIN_LOGIN_LENGTH, max = MAX_LENGTH)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	private String login;

	@NotBlank
	@Size(min = MIN_PASSWORD_LENGTH, max = MAX_LENGTH)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	private String password;

	@NotBlank
	@Email(regexp = "^((?!_)(?!\\.))[a-z0-9._]+[a-z0-9]+@{1}\\w+\\.com{1}(\\.br)*$")
	private String email;

	@Valid
	private Address address;

	@Null
	private String role;

	private boolean active;

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
