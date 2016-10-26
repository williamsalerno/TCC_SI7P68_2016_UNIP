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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.com.timetrialfactory.maestro.models.embeddables.Address;
import br.com.timetrialfactory.maestro.models.enums.RoleType;

@Entity
@Table(name = "users", catalog = "users")
public class User {

	private static final byte MIN_LOGIN_LENGTH = 3;
	private static final byte MIN_PASSWORD_LENGTH = 6;
	private static final byte MAX_LENGTH = 12;
	private static final byte MAX_EMAIL_LENGTH = 100;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@NotBlank
	@Pattern(regexp = "\\p{L}+")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank
	@Pattern(regexp = "\\p{L}+")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotBlank
	@Size(min = MIN_LOGIN_LENGTH, max = MAX_LENGTH)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	@Column(name = "username", nullable = false, unique = true)
	private String login;

	@NotBlank
	@Size(min = MIN_PASSWORD_LENGTH, max = MAX_LENGTH)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	@Column(name = "password", nullable = false)
	private String password;

	@NotBlank
	@Email(regexp = "^((?!_)(?!\\.))[a-z0-9._]+[a-z0-9]+@{1}\\w+\\.com{1}(\\.br)*$")
	@Size(max = MAX_EMAIL_LENGTH)
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

	@Column(name = "activationCode", nullable = false)
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
