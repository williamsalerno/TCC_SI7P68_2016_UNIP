package br.com.springmvc.timetrialfactory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springmvc.timetrialfactory.daos.GameDAO;
import br.com.springmvc.timetrialfactory.models.Game;

@Controller
public class GamesController {
	
	@Autowired
	private GameDAO gameDAO;

	@RequestMapping("/games")
	public String save(Game game) {
		System.out.println("Cadastrando jogo " + game);
		gameDAO.save(game);
		return "games/ok";
	}

	@RequestMapping("/games/form")
	public String gamesForm() {
		return "games/form";
	}

}
