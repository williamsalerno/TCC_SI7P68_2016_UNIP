package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.timetrialfactory.models.Game;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;
import br.com.springmvc.timetrialfactory.models.ShoppingItem;
import br.com.springmvc.timetrialfactory.services.GameService;

@Controller
@RequestMapping("/shopping")
@Scope(value = SCOPE_REQUEST)
public class ShoppingCartController {

	@Autowired
	private GameService service;

	@Autowired
	private ShoppingCart shoppingCart;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Long gameId) {
		ShoppingItem item = createItem(gameId);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/games");
	}

	private ShoppingItem createItem(Long id) {
		Game game = (Game) service.findGameById(id);
		ShoppingItem item = new ShoppingItem(game);
		return item;
	}

	@RequestMapping(method = RequestMethod.GET, value="/cart")
	public ModelAndView items() {
		ModelAndView modelAndView = new ModelAndView("shoppingCart/items");
		modelAndView.addObject("listadejogos", new Game());
		return modelAndView;
	}

}
