package br.com.springmvc.timetrialfactory.validation;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.springmvc.timetrialfactory.models.Game;

public class GameValidator implements Validator {

	private static final BigDecimal MINIMUM_PRICE = new BigDecimal("1");

	@Override
	public boolean supports(Class<?> clazz) {
		return Game.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		rejectIfEmptyOrWhitespace(errors, "title", "message.notnull_field");
		rejectIfEmptyOrWhitespace(errors, "price", "message.notnull_field");

		Game game = (Game) target;
		if (game.getPrice().compareTo(MINIMUM_PRICE) > 1) {
			errors.rejectValue("price", "field.denied");
		}
	}

}
