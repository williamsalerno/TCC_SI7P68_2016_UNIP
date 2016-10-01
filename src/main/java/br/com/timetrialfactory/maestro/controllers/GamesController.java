package br.com.timetrialfactory.maestro.controllers;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.timetrialfactory.maestro.models.Game;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.services.GameService;

@Controller
@Transactional
@Scope(value = SCOPE_REQUEST)
@RequestMapping("/games")
public class GamesController {

	private static final String REDIRECT_LOGOUT = "redirect:/logout";

	@Autowired
	private GameService gameService;

	// Método POST para salvar um novo jogo no bd.
	@RequestMapping(method = POST, value = "/newGame")
	@CacheEvict(value = "games", allEntries = true)
	public ModelAndView save(@Validated Game game, BindingResult result, RedirectAttributes attr, HttpSession session) {
		if (!isAdminLogged((LoggedUser) session.getAttribute("loggedUser"))) {
			return new ModelAndView(REDIRECT_LOGOUT);
		}
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.game", result);
			return new ModelAndView("games/newGame");
		}
		gameService.saveGame(game);
		return new ModelAndView("redirect:list");
	}

	// Método GET para carregar a página de form de novo jogo.
	@RequestMapping(method = RequestMethod.GET, value = "/new")
	public ModelAndView gamesForm(HttpSession session) {
		if (!isAdminLogged((LoggedUser) session.getAttribute("loggedUser"))) {
			return new ModelAndView(REDIRECT_LOGOUT);
		}
		ModelAndView modelAndView = new ModelAndView("games/newGame");
		modelAndView.addObject("game", new Game());
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
	public ModelAndView edit(@ModelAttribute Game game, HttpSession session) {
		if (!isAdminLogged((LoggedUser) session.getAttribute("loggedUser"))) {
			return new ModelAndView(REDIRECT_LOGOUT);
		}
		ModelAndView modelAndView = new ModelAndView("games/edit");
		modelAndView.addObject("game", game);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
	public ModelAndView delete(@ModelAttribute Game game, HttpSession session) {
		if (!isAdminLogged((LoggedUser) session.getAttribute("loggedUser"))) {
			return new ModelAndView(REDIRECT_LOGOUT);
		}
		gameService.deleteGame(game);
		return this.list();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}", name = "game")
	public ModelAndView update(@Valid Game game, BindingResult result, RedirectAttributes attr, HttpSession session) {
		if (!isAdminLogged((LoggedUser) session.getAttribute("loggedUser"))) {
			return new ModelAndView(REDIRECT_LOGOUT);
		}
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.game", result);
			return new ModelAndView("games/edit");
		} else {
			gameService.updateGame(game);
			return this.list();
		}
	}

	private boolean isAdminLogged(LoggedUser loggedUser) {
		if (loggedUser != null) {
			if (!loggedUser.isAdmin()) {
				return false;
			}
			return true;
		}
		return false;
	}

}
