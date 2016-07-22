package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.timetrialfactory.daos.UserDAO;
import br.com.springmvc.timetrialfactory.models.User;
import br.com.springmvc.timetrialfactory.validation.UserValidator;

@Controller
@Transactional
@RequestMapping("/")
public class UsersController {

	@Autowired
	private UserDAO userDao;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}

	@RequestMapping(method = POST, value = "/success")
	public ModelAndView newUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("users/newUser");
		} else {
			userDao.save(user);
			redirectAttributes.addFlashAttribute("success", "message.success");
			return new ModelAndView("redirect:login");
		}

	}

	@RequestMapping(method = GET, value = "/user")
	public ModelAndView login(@Valid User user) {
		userDao.load(user.getId());
		ModelAndView modelAndView = new ModelAndView("games/list");
		return modelAndView;
	}

	@RequestMapping(method = GET, value = "/login")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView("users/login");
		return modelAndView;
	}

}
