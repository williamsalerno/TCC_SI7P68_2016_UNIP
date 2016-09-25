package br.com.timetrialfactory.maestro.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class ImageController {

	@RequestMapping(method = RequestMethod.GET, value = "/{gameId}/image")
	public ModelAndView showImage(@PathVariable Long gameId) throws IOException {
		ModelAndView modelAndView = new ModelAndView("games/list");
		return modelAndView;

	}
}
