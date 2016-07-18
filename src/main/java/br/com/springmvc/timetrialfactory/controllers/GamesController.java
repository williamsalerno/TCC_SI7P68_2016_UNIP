package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.timetrialfactory.daos.GameDAO;
import br.com.springmvc.timetrialfactory.models.Game;

@Controller
@Transactional
@RequestMapping("/games")
public class GamesController {

	@Autowired
	private GameDAO gameDao;

	// Método POST para salvar um novo jogo no bd.
	@RequestMapping(method = POST, value = "/newGame")
	@CacheEvict(value = "games", allEntries = true)
	public ModelAndView save(@Valid Game game, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return gamesForm(game);
		}
		gameDao.save(game);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:list");
	}

	// Método GET para carregar a página de form de novo jogo.
	@RequestMapping(method = RequestMethod.GET, value = "/form")
	public ModelAndView gamesForm(Game game) {
		ModelAndView modelAndView = new ModelAndView("games/form");
		return modelAndView;
	}

	// Método GET para carregar a lista de jogos do bd.
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@Cacheable(value = "games")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("games/list");
		modelAndView.addObject("games", gameDao.list());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/details/{id}")
	public ModelAndView show(Long id) {
		ModelAndView modelAndView = new ModelAndView("games/details");
		Game game = (Game) gameDao.load(id);
		modelAndView.addObject("game", game);
		return modelAndView;
	}

}
