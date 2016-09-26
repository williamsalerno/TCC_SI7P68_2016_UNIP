package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.timetrialfactory.maestro.models.LoggedUser;

@Controller("user")
@Scope(value = SCOPE_REQUEST)
@RequestMapping("/user")
public class UserController {

	@RequestMapping(method = RequestMethod.GET, value = "/myProfile")
	public ModelAndView loggedUser(HttpSession session) {
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView("users/myProfile");
		modelAndView.getModel().put("user", loggedUser);
		return modelAndView;
	}

}
