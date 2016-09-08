package br.com.timetrialfactory.maestro.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.timetrialfactory.maestro.models.embeddables.Address;
import br.com.timetrialfactory.maestro.models.enums.Country;

public class AddressValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Address.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Preconditions.checkNotNull(target, "message.denied_value");
		Address address = (Address) target;
		if (address.getCountry() == null || (!address.getCountry().getName().equals(Country.BRAZIL.getName()) && !address.getCountry().getName().equals(Country.OTHER.getName()))) {
			errors.rejectValue("country", "message.denied_value");
		}
	}

}
