package br.com.timetrialfactory.maestro.controllers;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.timetrialfactory.maestro.dto.GameDTO;
import br.com.timetrialfactory.maestro.dto.LicenseDTO;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.ShoppingCart;
import br.com.timetrialfactory.maestro.models.ShoppingItem;
import br.com.timetrialfactory.maestro.services.GameService;
import br.com.timetrialfactory.maestro.services.LicenseService;
import br.com.timetrialfactory.maestro.services.PurchaseService;

@Controller
@RequestMapping("/shopping/cart")
@Scope(value = SCOPE_REQUEST)
public class ShoppingCartController {

	@Autowired
	private GameService gameService;

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private LicenseService licenseService;

	@RequestMapping(method = RequestMethod.POST, value = "/addGame")
	public ModelAndView add(final Long gameId) {
		ShoppingItem item = createItem(gameId);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/shopping/cart/view");
	}

	private ShoppingItem createItem(final Long id) {
		GameDTO game = gameService.findGameById(id);
		ShoppingItem item = new ShoppingItem(game);
		return item;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view")
	public ModelAndView items() {
		ModelAndView modelAndView = new ModelAndView("shoppingCart/items");
		modelAndView.addObject("shoppingCart", shoppingCart);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkout")
	public ModelAndView checkout(HttpSession session) {
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		if (loggedUser.isLogged()) {
			ModelAndView modelAndView = new ModelAndView("shoppingCart/download");
			purchaseService.savePurchase(shoppingCart, loggedUser);
			licenseService.saveLicense(loggedUser, shoppingCart.getItems());
			for (ShoppingItem item : shoppingCart.getItems()) {
				LicenseDTO dto = new LicenseDTO();
				dto.setGameId(item.getGame().getId());
				loggedUser.getLicenses().add(dto);
			}
			shoppingCart.getItems().clear();
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/logout");
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView removeFromCart(final int gameId) {
		shoppingCart.remove(gameId);
		return new ModelAndView("redirect:/shopping/cart/view");
	}
}
