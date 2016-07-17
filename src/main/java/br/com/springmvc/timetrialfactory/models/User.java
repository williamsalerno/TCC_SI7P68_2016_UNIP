package br.com.springmvc.timetrialfactory.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import br.com.springmvc.timetrialfactory.models.embeddables.Address;

@Entity
@Table(name="users", catalog="users")
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@NotNull
	@Column(name = "first_name", nullable = false)
	@Pattern(regexp = "[a-zA-Z]+", message = "O nome deve conter apenas letras.")
	private String firstName;

	@NotNull
	@Column(name = "last_name", nullable = false)
	@Pattern(regexp = "[a-zA-Z]+", message = "O nome deve conter apenas letras.")
	private String lastName;

	@NotNull
	@Size(min = 3, max = 12, message = "")
	@Column(name = "username", nullable = false, unique = true)
	private String login;

	@NotNull
	@Size(min = 6, max = 12, message = "")
	@Column(name = "password", nullable = false)
	private String password;

	@NotNull
	@Email(regexp = "[a-z]+@{1}\\w+\\.com{1}(\\.br)*", message = "O email informado é inválido.")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Embedded
	@Valid
	private Address address;

	@Column(name = "is_admin", nullable = false)
	private Boolean admin = false;

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

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	

}
