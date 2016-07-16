package br.com.springmvc.timetrialfactory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String index(){
		System.out.println("carregando página");
		return "hello-world";
	}

}
