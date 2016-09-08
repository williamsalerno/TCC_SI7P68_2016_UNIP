package br.com.timetrialfactory.maestro.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.timetrialfactory.maestro.models.User;
import br.com.timetrialfactory.maestro.models.enums.Country;

@Component
public class UserValidator implements Validator {

	private static final String REGEX_NAMES = "[a-zA-Z]+";
	private static final String REGEX_CITY_OR_STATE = "[a-zA-Z ]+";
	private static final String REGEX_EMAIL = "^((?!_)(?!\\.))[a-z0-9._]+[a-z0-9]+@{1}\\w+\\.com{1}(\\.br)*$";
	private static final String REGEX_CEP = "\\d{8,8}";
	private static final int MIN_LOGIN_LENGTH = 3;
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final int MAX_LENGTH = 12;

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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.country", "message.notnull_field");

		User user = (User) target;
		if (!(user.getFirstName().matches(REGEX_NAMES))) {
			errors.rejectValue("firstName", "message.firstName.denied_value");
		}
		if (!user.getLastName().matches(REGEX_NAMES)) {
			errors.rejectValue("lastName", "message.lastName.denied_value");
		}
		if (!(user.getLogin().length() >= MIN_LOGIN_LENGTH && user.getLogin().length() <= MAX_LENGTH)) {
			errors.rejectValue("login", "message.login.denied_value");
		}
		if (!(user.getPassword().length() >= MIN_PASSWORD_LENGTH && user.getPassword().length() <= MAX_LENGTH)) {
			errors.rejectValue("password", "message.password.denied_value");
		}
		if (!(user.getEmail().matches(REGEX_EMAIL))) {
			errors.rejectValue("email", "message.email.denied_value");
		}
		if (!(user.getAddress().getCountry().equals(Country.BRAZIL)
				|| user.getAddress().getCountry().equals(Country.OTHER))) {
			errors.rejectValue("address.country", "message.country.denied_value");
		}
		if (!(user.getAddress().getState().matches(REGEX_CITY_OR_STATE))) {
			errors.rejectValue("address.state", "message.denied_value");
		}
		if (!(user.getAddress().getCity().matches(REGEX_CITY_OR_STATE))) {
			errors.rejectValue("address.city", "message.denied_value");
		}
		if (!(user.getAddress().getCep().matches(REGEX_CEP))) {
			errors.rejectValue("address.cep", "message.cep.denied_value");
		}
	}

}
