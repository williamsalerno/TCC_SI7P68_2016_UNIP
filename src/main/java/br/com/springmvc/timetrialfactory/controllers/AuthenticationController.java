package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.models.User;
import br.com.springmvc.timetrialfactory.services.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService service;
	
	@Autowired
	private LoggedUser loggedUser;

	@RequestMapping(method = GET, value = "/login")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView("users/login");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(method = POST, value = "/login")
	public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) {
		User userToVerify = (User) service.loadUser(user.getLogin(), user.getPassword());
		if (userToVerify == null) {
			ModelAndView modelAndView = new ModelAndView("users/login");
			modelAndView.addObject("loginError", true);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/games/list");
			loggedUser.login(userToVerify);
			session.setAttribute("loggedUser", loggedUser);
			System.out.println(loggedUser.toString());
			return modelAndView;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public ModelAndView logout(LoggedUser loggedUser, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		loggedUser.logout();
		session.invalidate();
		return modelAndView;
	}
}
