package br.com.springmvc.timetrialfactory.utils;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsVerifierUtil {

	private ErrorsVerifierUtil() {
	}

	public static ModelAndView verify(@Valid Object toVerify, BindingResult result, ModelAndView errorView) {
		if (result.hasErrors()) {
			return errorView;
		} else {
			return null;
		}
	}

}
