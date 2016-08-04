package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.timetrialfactory.models.User;
import br.com.springmvc.timetrialfactory.models.embeddables.Address;
import br.com.springmvc.timetrialfactory.services.UserService;
import br.com.springmvc.timetrialfactory.validation.AddressValidator;

@Controller
@Transactional
@RequestMapping("/newUser")
public class VisitorController {

	@Autowired
	private UserService service;

	@InitBinder("address")
	protected void initAddressBinder(WebDataBinder binder) {
		binder.setValidator(new AddressValidator());
	}

	@RequestMapping(method = POST, value = "/form", name = "user")
	public ModelAndView newUser(@Valid User user, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			ModelAndView modelAndView = new ModelAndView("users/newUser");
			modelAndView.addObject("settedCountry", user.getAddress().getCountry().getName());
			return modelAndView;
		} else if (!service.saveUser(user)) {
			ModelAndView modelAndView = new ModelAndView("users/newUser");
			modelAndView.addObject("error", true);
			modelAndView.addObject("settedCountry", user.getAddress().getCountry().getName());
			return modelAndView;
		} else {
			attr.addFlashAttribute("success", true);
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(method = GET, value = "/form")
	public ModelAndView newUserForm(@Validated Address address, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.address", result);
			return new ModelAndView("redirect:/selectCountry");
		} else {
			ModelAndView modelAndView = new ModelAndView("users/newUser");
			modelAndView.addObject("country", address);
			modelAndView.addObject("user", new User());
			return modelAndView;
		}
	}

	@RequestMapping(method = GET, value = "/selectCountry")
	public ModelAndView selectCountry() {
		ModelAndView modelAndView = new ModelAndView("users/selectCountry");
		return modelAndView;
	}

}
