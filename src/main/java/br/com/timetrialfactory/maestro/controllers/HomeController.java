package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.timetrialfactory.maestro.services.GameService;

@Controller
public class HomeController {

	@Autowired
	private GameService gameService;

	@RequestMapping(method = GET, value = "/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("home/index");
		modelAndView.addObject("games", gameService.listGames());
		return modelAndView;
	}

	@RequestMapping(method = GET, value = "/aboutUs")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView("home/about");
		return modelAndView;
	}

	@RequestMapping(method = GET, value = "/support")
	public ModelAndView support() {
		ModelAndView modelAndView = new ModelAndView("home/support");
		return modelAndView;
	}

}
