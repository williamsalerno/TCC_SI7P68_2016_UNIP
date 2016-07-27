package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.timetrialfactory.models.User;
import br.com.springmvc.timetrialfactory.services.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService service;

	@RequestMapping(method = GET, value = "/login")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView("users/login");
		return modelAndView;
	}

	@RequestMapping(method = POST, value = "/login")
	public ModelAndView login(@RequestParam("login") String login, @RequestParam("password") String password,
			HttpSession session) {
		User user = service.load(login);
		if (user == null) {
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
