package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.timetrialfactory.daos.UserDAO;
import br.com.springmvc.timetrialfactory.models.User;
import br.com.springmvc.timetrialfactory.models.embeddables.Address;
import br.com.springmvc.timetrialfactory.validation.AddressValidator;
import br.com.springmvc.timetrialfactory.validation.UserValidator;

@Controller
@Transactional
@RequestMapping("/")
public class UsersController {

	private final Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UserDAO userDao;

	@Autowired
	private UserValidator userValidator;

	@InitBinder("address")
	protected void initAddressBinder(WebDataBinder binder) {
		binder.setValidator(new AddressValidator());
	}

	@InitBinder("user")
	protected void initUserBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(method = POST, value = "/success", name = "user")
	public ModelAndView newUser(@ModelAttribute("user") @Validated final User user, final BindingResult result,
			RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			attr.addAttribute("country", user.getAddress().getCountry());
			attr.addFlashAttribute("user", user);
			return new ModelAndView("redirect:/newUser/form");
		} else {
			userDao.save(user);
			attr.addFlashAttribute("success", "message.success");
			return new ModelAndView("redirect:login");
		}

	}

	@RequestMapping(method = GET, value = "/user")
	public ModelAndView login(@Validated User user) {
		userDao.load(user.getId());
		ModelAndView modelAndView = new ModelAndView("games/list");
		return modelAndView;
	}

	@RequestMapping(method = GET, value = "/login")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView("users/login");
		return modelAndView;
	}

	@RequestMapping(method = GET, value = "/newUser/form")
	public ModelAndView newUserForm(@Validated Address address, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.address", result);
			return new ModelAndView("redirect:/newUser/selectCountry");
		} else {
			ModelAndView modelAndView = new ModelAndView("users/newUser");
			modelAndView.addObject("country", address);
			modelAndView.addObject("user", new User());
			return modelAndView;
		}
	}

	@RequestMapping(method = GET, value = "/newUser/selectCountry")
	public ModelAndView selectCountry() {
		ModelAndView modelAndView = new ModelAndView("users/selectCountry");
		return modelAndView;
	}

}
