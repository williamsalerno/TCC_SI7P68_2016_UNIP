package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.timetrialfactory.maestro.dto.LicenseDTO;
import br.com.timetrialfactory.maestro.models.Game;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.services.GameService;

@Controller("user")
@Scope(value = SCOPE_REQUEST)
@RequestMapping("/user")
public class UserController {

	@Autowired
	private GameService service;

	@RequestMapping(method = RequestMethod.GET, value = "/myProfile")
	public ModelAndView loggedUser(HttpSession session) {
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		if (loggedUser != null) {
			ModelAndView modelAndView = new ModelAndView("users/myProfile");
			modelAndView.getModel().put("user", loggedUser);
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/logout");
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/myGames")
	public ModelAndView myGames(HttpSession session) {
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		if (loggedUser != null) {
			ModelAndView modelAndView = new ModelAndView("users/myGames");
			modelAndView.getModel().put("user", loggedUser);
			Set<Game> gamesList = this.listUserGames(service.listGames(), loggedUser.getLicenses());
			modelAndView.getModel().put("games", gamesList);
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/logout");
		}
	}

	private Set<Game> listUserGames(Set<Game> gamesList, Set<LicenseDTO> licenseList) {
		Set<Game> games = new HashSet<Game>();
		for (Game game : gamesList) {
			if (licenseList.iterator().next().getGameId() == game.getId()) {
				games.add(game);
			}
		}
		return games;
	}

}
