package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.timetrialfactory.daos.UserDAO;
import br.com.springmvc.timetrialfactory.models.User;

@Controller
@Transactional
@RequestMapping("/")
public class UsersController {

	@Autowired
	private UserDAO userDao;

	@RequestMapping(method = POST, value = "/login")
	public ModelAndView login(User user) {
		userDao.load(user.getId());
		ModelAndView modelAndView = new ModelAndView("games/list");
		return modelAndView;
	}

	@RequestMapping(method = GET, value = "/login")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView("users/login");
		return modelAndView;
	}
	
	@RequestMapping(method = GET, value = "/newUser/selectCountry")
	public ModelAndView selectCountry() {
		ModelAndView modelAndView = new ModelAndView("users/selectCountry");
		return modelAndView;
	}

}
