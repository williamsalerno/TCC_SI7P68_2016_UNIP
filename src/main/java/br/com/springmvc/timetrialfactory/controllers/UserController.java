package br.com.springmvc.timetrialfactory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.models.User;

@Controller("user")
@SessionAttributes("user")
public class UserController {

	@RequestMapping("/myProfile")
	public ModelAndView loggedUser(@ModelAttribute("user") LoggedUser user) {
		ModelAndView modelAndView = new ModelAndView("users/myProfile");
		modelAndView.getModel().put("user", user);
		return modelAndView;
	}

}
