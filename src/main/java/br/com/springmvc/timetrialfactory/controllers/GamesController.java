package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.timetrialfactory.models.Game;
import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.services.GameService;
import br.com.springmvc.timetrialfactory.validation.GameValidator;

@Controller
@Transactional
@Scope(value = SCOPE_REQUEST)
@RequestMapping("/games")
public class GamesController {

	@Autowired
	private GameService gameService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new GameValidator());
	}

	// Método POST para salvar um novo jogo no bd.
	@RequestMapping(method = POST, value = "/newGame")
	@CacheEvict(value = "games", allEntries = true)
	public ModelAndView save(@Validated Game game, BindingResult result, RedirectAttributes redirectAttributes,
			HttpSession session) {
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		if (loggedUser.isAdmin()) {
			if (result.hasErrors()) {
				return gamesForm(session);
			}
			gameService.saveGame(game);
			redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
			return new ModelAndView("redirect:list");
		}
		return new ModelAndView("redirect:/logout");
	}

	// Método GET para carregar a página de form de novo jogo.
	@RequestMapping(method = RequestMethod.GET, value = "/new")
	public ModelAndView gamesForm(HttpSession session) {
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		if (loggedUser.isAdmin()) {
			ModelAndView modelAndView = new ModelAndView("games/newGame");
			modelAndView.addObject("game", new Game());
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/logout");
		return modelAndView;
	}

	// Método GET para carregar a lista de jogos do bd.
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@Cacheable(value = "games")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("games/list");
		modelAndView.addObject("games", gameService.listGames());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/details/{id}")
	public ModelAndView show(Long id) {
		ModelAndView modelAndView = new ModelAndView("games/details");
		Game game = (Game) gameService.findGameById(id);
		modelAndView.addObject("game", game);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute Game game) {
		ModelAndView modelAndView = new ModelAndView("games/edit");
		modelAndView.addObject("game", game);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
	public ModelAndView delete(@ModelAttribute Game game) {
		gameService.deleteGame(game);
		return this.list();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}", name = "game")
	public ModelAndView update(@Valid Game game, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.game", result);
			return new ModelAndView("redirect:/edit/{id}");
		} else {
			gameService.updateGame(game);
			return this.list();
		}
	}

}
