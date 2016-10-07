package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.timetrialfactory.maestro.email.EmailSender;
import br.com.timetrialfactory.maestro.models.User;
import br.com.timetrialfactory.maestro.services.UserService;

@Controller
@RequestMapping(value = "/support")
public class SupportController {

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private UserService userService;

	@RequestMapping(method = GET)
	public ModelAndView support() {
		ModelAndView modelAndView = new ModelAndView("home/support");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(method = POST, value = "/forgotMyPassword/recover")
	public ModelAndView recoverPassword(@ModelAttribute User user) {
		ModelAndView modelAndView = new ModelAndView("home/support");
		User userToFind = userService.findByEmailAndUsername(user.getEmail(), user.getLogin());
		if (userToFind == null) {
			modelAndView.addObject("recoverError", true);
			return modelAndView;
		} else {
			emailSender.sendRecoveryPasswordEmail(userToFind.getLogin(), userToFind.getEmail(),
					userToFind.getPassword());
			modelAndView.addObject("passwordRecover", true);
			return modelAndView;
		}
	}
}
