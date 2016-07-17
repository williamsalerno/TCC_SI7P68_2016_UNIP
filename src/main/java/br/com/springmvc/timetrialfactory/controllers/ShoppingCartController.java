package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.timetrialfactory.daos.GameDAO;
import br.com.springmvc.timetrialfactory.models.Game;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;
import br.com.springmvc.timetrialfactory.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
@Scope(value = SCOPE_REQUEST)
public class ShoppingCartController {

	@Autowired
	private GameDAO gameDao;

	@Autowired
	private ShoppingCart shoppingCart;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Long gameId) {
		ShoppingItem item = createItem(gameId);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/games");
	}

	private ShoppingItem createItem(Long gameId) {
		Game game = (Game) gameDao.load(gameId);
		ShoppingItem item = new ShoppingItem(game);
		return item;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String items(){
		return "shoppingCart/items";
	}

}
