package br.com.timetrialfactory.maestro.models.embeddables;

import static br.com.timetrialfactory.maestro.utils.FormatterStringUtil.formatToUTF8;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.timetrialfactory.maestro.models.enums.Country;

@Embeddable
public class Address {

	private static final byte CEP_LENGTH = 8;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "address_Country", nullable = false)
	private Country country;

	@Pattern(regexp = "[A-Za-zÀ-ú ]+")
	@Column(name = "address_State")
	private String state;

	@Pattern(regexp = "[A-Za-zÀ-ú ]+")
	@Column(name = "address_City")
	private String city;

	@Pattern(regexp = "\\d{8,8}")
	@Size(min = CEP_LENGTH, max = CEP_LENGTH)
	@Column(name = "address_Cep")
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
