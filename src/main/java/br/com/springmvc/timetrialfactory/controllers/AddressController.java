package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.timetrialfactory.models.embeddables.Address;
import br.com.springmvc.timetrialfactory.validation.AddressValidator;

@Controller
@RequestMapping("/")
public class AddressController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new AddressValidator());
	}

	@RequestMapping(method = GET, value = "/newUser/form")
	public ModelAndView newUserForm(@Valid Address address, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("users/selectCountry");
		} else {
			ModelAndView modelAndView = new ModelAndView("users/newUser");
			modelAndView.addObject("country", address);
			return modelAndView;
		}
	}
}
