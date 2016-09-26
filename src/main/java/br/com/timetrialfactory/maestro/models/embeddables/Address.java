package br.com.timetrialfactory.maestro.models.embeddables;

import static br.com.timetrialfactory.maestro.utils.FormatterStringUtil.formatToUTF8;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.timetrialfactory.maestro.models.enums.Country;

@Embeddable
public class Address {

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "address_Country", nullable = false)
	private Country country;

	@Column(name = "address_State")
	@Pattern(regexp = "\\p{L} +")
	private String state;

	@Column(name = "address_City")
	@Pattern(regexp = "\\p{L} +")
	private String city;

	@Column(name = "address_Cep")
	@Pattern(regexp = "\\d{8,8}")
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
		this.state = formatToUTF8(state);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = formatToUTF8(city);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.cep).toHashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Address)) {
			return false;
		}
		Address other = (Address) obj;
		return new EqualsBuilder().append(this.cep, other.cep).isEquals();
	}

	public String toString() {
		return new ToStringBuilder(this).append("country", this.country).append("city", this.city)
				.append("state", this.state).append("cep", this.cep).build();
	}

}
