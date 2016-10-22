package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.timetrialfactory.maestro.dto.UserDTO;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.services.LicenseService;
import br.com.timetrialfactory.maestro.services.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private LicenseService licenseService;

	@RequestMapping(method = GET, value = "/login")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView("users/login");
		modelAndView.addObject("user", new UserDTO());
		return modelAndView;
	}

	@RequestMapping(method = POST, value = "/login")
	public ModelAndView login(@RequestParam String login, @RequestParam String password, HttpSession session) {
		UserDTO userToVerify = userService.loadUser(login, password);
		return this.verifyUserToLogin(userToVerify, session);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public ModelAndView logout(LoggedUser loggedUser, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		loggedUser.logout();
		session.invalidate();
		return modelAndView;
	}

	private LoggedUser setLoggedUser(UserDTO userToVerify) {
		LoggedUser loggedUser = new LoggedUser();
		loggedUser.login(userToVerify);
		loggedUser.setLicenses(licenseService.listUserLicenses(loggedUser.getId()));
		return loggedUser;
	}

	private ModelAndView verifyUserToLogin(UserDTO userToVerify, HttpSession session) {
		if (userToVerify == null) {
			ModelAndView modelAndView = new ModelAndView("users/login");
			modelAndView.addObject("loginError", true);
			return modelAndView;
		} else if (!userToVerify.isActive()) {
			ModelAndView modelAndView = new ModelAndView("users/login");
			modelAndView.addObject("activationError", true);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/games/list");
			session.setAttribute("loggedUser", this.setLoggedUser(userToVerify));
			return modelAndView;
		}
	}
}
