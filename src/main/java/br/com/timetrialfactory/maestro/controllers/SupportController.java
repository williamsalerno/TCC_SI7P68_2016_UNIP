package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.timetrialfactory.maestro.dto.UserDTO;
import br.com.timetrialfactory.maestro.email.EmailSender;
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
		return modelAndView;
	}

	@RequestMapping(method = POST, value = "/forgotMyPassword/recover")
	public ModelAndView recoverPassword(@RequestParam String login, @RequestParam String email) {
		ModelAndView modelAndView = new ModelAndView("home/support");
		UserDTO userToFind = userService.findByEmailAndUsername(email, login);
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

	@RequestMapping(method = POST, value = "/message")
	public ModelAndView sendMessage(@RequestParam String sender, @RequestParam String subject,
			@RequestParam String message) {
		ModelAndView modelAndView = new ModelAndView("home/support");
		emailSender.sendMessage(sender, subject, message);
		modelAndView.addObject("user", new UserDTO());
		modelAndView.addObject("messageSuccess", true);
		return modelAndView;
	}
}
