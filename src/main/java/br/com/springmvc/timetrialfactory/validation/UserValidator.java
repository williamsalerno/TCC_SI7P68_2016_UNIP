package br.com.springmvc.timetrialfactory.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.springmvc.timetrialfactory.models.User;
import br.com.springmvc.timetrialfactory.models.enums.Country;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Preconditions.checkNotNull(target, "message.denied_value");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "message.notnull_field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "message.notnull_field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "message.notnull_field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.notnull_field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "message.notnull_field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "message.notnull_field");
		User user = (User) target;
		if (!user.getAddress().getCountry().equals(Country.BRAZIL)
				|| !user.getAddress().getCountry().equals(Country.OTHER)) {
			errors.rejectValue("address", "message.denied_value");
		}
	}

}
