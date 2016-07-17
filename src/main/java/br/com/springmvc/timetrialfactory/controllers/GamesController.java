package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.timetrialfactory.daos.GameDAO;
import br.com.springmvc.timetrialfactory.models.Game;
import br.com.springmvc.timetrialfactory.validation.GameValidator;

@Controller
@Transactional
@RequestMapping("/games")
public class GamesController {
	
	@Autowired
	private GameDAO gameDao;
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder){
//		binder.setValidator(new GameValidator());
//	}

	@RequestMapping(method=POST, name="newGame")
	public ModelAndView save(@Valid Game game, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
			return gamesForm(game);
		}
		gameDao.save(game);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:games");
	}

	@RequestMapping("/form")
	public ModelAndView gamesForm(Game game) {
		ModelAndView modelAndView = new ModelAndView("games/form");
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("games/list");
		modelAndView.addObject("games", gameDao.list());
		return modelAndView;
	}

}
