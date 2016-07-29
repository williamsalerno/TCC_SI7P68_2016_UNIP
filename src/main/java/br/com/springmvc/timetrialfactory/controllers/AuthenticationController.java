package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.timetrialfactory.models.User;

@Controller
public class AuthenticationController {

	@Autowired
	private UserDetailsService detailsService;

	@RequestMapping(method = GET, value = "/login")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView("users/login");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(method = POST, value = "/login")
	public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) {
		User userToVerify = (User) detailsService.loadUserByUsername(user.getLogin());
		if (userToVerify == null) {
			ModelAndView modelAndView = new ModelAndView("users/login");
			modelAndView.addObject("loginError", true);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/games/list");
			session.setAttribute("user", user);
			return modelAndView;
		}
	}

}
