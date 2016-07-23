package br.com.springmvc.timetrialfactory.models.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.springmvc.timetrialfactory.models.enums.Country;

@Embeddable
public class Address {

	@Enumerated(EnumType.STRING)
	@NotNull(message = "{message.notnull_field}")
	@Column(name = "address_Country", nullable = false)
	private Country country;

	@Column(name = "address_State")
	@Pattern(regexp = "[a-zA-Z ]+")
	private String state;

	@Column(name = "address_City")
	@Pattern(regexp = "[a-zA-Z ]+")
	private String city;

	@Column(name = "address_Cep")
	@Pattern(regexp = "\\d{8,8}", message = "mensagem erro cep")
	private String cep;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
