package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.timetrialfactory.maestro.assembler.UserAssembler;
import br.com.timetrialfactory.maestro.dto.GameDTO;
import br.com.timetrialfactory.maestro.dto.LicenseDTO;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.User;
import br.com.timetrialfactory.maestro.services.GameService;
import br.com.timetrialfactory.maestro.services.UserService;

@Controller("user")
@Scope(value = SCOPE_REQUEST)
@RequestMapping("/user")
public class UserController {

	@Autowired
	private GameService service;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/myProfile")
	public ModelAndView loggedUser(HttpSession session) {
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		if (loggedUser != null) {
			ModelAndView modelAndView = new ModelAndView("users/myProfile");
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
			Set<GameDTO> gamesList = this.listUserGames(service.listGames(), loggedUser.getLicenses());
			modelAndView.getModel().put("games", gamesList);
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/logout");
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/myInfo")
	public ModelAndView myInfo(HttpSession session) {
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		if (loggedUser != null) {
			loggedUser.getLoggedUser().setPassword("");
			ModelAndView modelAndView = new ModelAndView("users/ownInfo");
			modelAndView.getModel().put("user", loggedUser.getLoggedUser());
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/logout");
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/myInfo/update")
	public ModelAndView update(@Valid User user, BindingResult result, RedirectAttributes attr, HttpSession session) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			ModelAndView modelAndView = new ModelAndView("users/ownInfo");
			modelAndView.addObject("settedCountry", user.getAddress().getCountry().getName());
			return modelAndView;
		} else {
			userService.updateUser(user);
			LoggedUser loggedUser = new LoggedUser();
			loggedUser.login(userService.findById(user.getId()));
			session.setAttribute("loggedUser", loggedUser);
			return new ModelAndView("redirect:/user/myProfile");
		}
	}

	private Set<GameDTO> listUserGames(Set<GameDTO> gamesList, Set<LicenseDTO> licenseList) {
		Set<GameDTO> games = new HashSet<GameDTO>();
		for (GameDTO game : gamesList) {
			for (LicenseDTO gameToCompare : licenseList) {
				if (gameToCompare.getGame().getId() == game.getId()) {
					games.add(game);
				}
			}
		}
		return games;
	}

}
