package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(method = GET, value = "/")
	public String index() {
		return "home/index";
	}

}