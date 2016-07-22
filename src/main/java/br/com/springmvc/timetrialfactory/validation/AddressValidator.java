package br.com.springmvc.timetrialfactory.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.springmvc.timetrialfactory.models.embeddables.Address;
import br.com.springmvc.timetrialfactory.models.enums.Country;

public class AddressValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Address.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Preconditions.checkNotNull(target, "message.denied_value");
		Address address = (Address) target;
		if (address.getCountry() == null || (!address.getCountry().equals(Country.BRAZIL) && !address.getCountry().equals(Country.OTHER))) {
			errors.rejectValue("country", "message.denied_value");
		}
	}

}
