package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.timetrialfactory.maestro.assembler.LicenseAssembler;
import br.com.timetrialfactory.maestro.assembler.UserAssembler;
import br.com.timetrialfactory.maestro.dto.UserDTO;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.User;
import br.com.timetrialfactory.maestro.services.LicenseService;
import br.com.timetrialfactory.maestro.services.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService service;

	@Autowired
	private LicenseService licenseService;

	@Autowired
	private UserAssembler assembler;

	@Autowired
	private LicenseAssembler licenseAssembler;

	@RequestMapping(method = GET, value = "/login")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView("users/login");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(method = POST, value = "/login")
	public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) {
		UserDTO userToVerify = assembler.toObject(service.loadUser(user.getLogin(), user.getPassword()));
		if (userToVerify == null) {
			ModelAndView modelAndView = new ModelAndView("users/login");
			modelAndView.addObject("loginError", true);
			return modelAndView; 
		} else {
			ModelAndView modelAndView = new ModelAndView("redirect:/games/list");
			LoggedUser loggedUser = new LoggedUser();
			loggedUser.login(userToVerify);
			loggedUser.setLicenses(licenseAssembler.toObject(licenseService.listUserLicenses(loggedUser.getId())));
			session.setAttribute("loggedUser", loggedUser);
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
