package br.com.springmvc.timetrialfactory.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;

import br.com.springmvc.timetrialfactory.models.embeddables.Address;
import br.com.springmvc.timetrialfactory.models.enums.RoleType;

@Entity
@Table(name = "users", catalog = "users")
public class User {

	private static final int MIN_LOGIN_LENGTH = 3;
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final int MAX_LENGTH = 12;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@NotNull
	@Column(name = "first_name", nullable = false)
	@Pattern(regexp = "[a-zA-Z]+")
	private String firstName;

	@NotNull
	@Column(name = "last_name", nullable = false)
	@Pattern(regexp = "[a-zA-Z]+")
	private String lastName;

	@NotNull
	@Size(min = MIN_LOGIN_LENGTH, max = MAX_LENGTH)
	@Column(name = "username", nullable = false, unique = true)
	private String login;

	@NotNull
	@Size(min = MIN_PASSWORD_LENGTH, max = MAX_LENGTH)
	@Column(name = "password", nullable = false)
	private String password;

	@NotNull
	@Email(regexp = "^((?!_)(?!\\.))[a-z0-9._]+[a-z0-9]+@{1}\\w+\\.com{1}(\\.br)*$")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Embedded
	@Valid
	private Address address;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private RoleType role;

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

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.id).append(this.login).append(this.email).toHashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return new EqualsBuilder().append(this.id, other.id).append(this.login, other.login)
				.append(this.email, other.email).isEquals();
	}

	public String toString() {
		return new ToStringBuilder(this).append("firstName", this.firstName).append("lastName", this.lastName)
				.append("email", this.email).append("address", this.address).append("login", this.login)
				.append("password", this.password)
				.append("role", this.role).build();
	}

}
