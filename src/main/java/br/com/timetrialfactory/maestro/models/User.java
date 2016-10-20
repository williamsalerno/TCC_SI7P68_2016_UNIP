package br.com.timetrialfactory.maestro.models;

import static br.com.timetrialfactory.maestro.utils.FormatterStringUtil.formatToUTF8;
import static java.lang.Boolean.FALSE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.timetrialfactory.maestro.models.embeddables.Address;
import br.com.timetrialfactory.maestro.models.enums.RoleType;

@Entity
@Table(name = "users", catalog = "users")
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "username", nullable = false, unique = true)
	private String login;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Embedded
	@Valid
	private Address address;

	@Enumerated(STRING)
	@Column(name = "role", nullable = false)
	private RoleType role;

	@Column(name = "active", nullable = false)
	private Boolean active = FALSE;

	@Column(name = "activationCode")
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
		this.firstName = formatToUTF8(firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = formatToUTF8(lastName);
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(Long activationCode) {
		this.activationCode = activationCode;
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
				.append("password", this.password).append("role", this.role).build();
	}

}
