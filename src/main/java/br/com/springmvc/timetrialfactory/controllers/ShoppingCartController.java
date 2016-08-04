package br.com.springmvc.timetrialfactory.controllers;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import java.util.UUID;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.paypal.api.payments.Payment;

import br.com.springmvc.timetrialfactory.apis.paypal.PayPalCall;
import br.com.springmvc.timetrialfactory.daos.PurchaseDAO;
import br.com.springmvc.timetrialfactory.models.Game;
import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;
import br.com.springmvc.timetrialfactory.models.ShoppingItem;
import br.com.springmvc.timetrialfactory.services.GameService;
import br.com.springmvc.timetrialfactory.services.PurchaseService;

@Controller
@RequestMapping("/shopping")
@Scope(value = SCOPE_REQUEST)
public class ShoppingCartController {

	@Autowired
	private GameService service;

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private PayPalCall payPal;

	@Autowired
	private PurchaseDAO purchaseDao;

	@Autowired
	private LoggedUser userWeb;

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

	@RequestMapping(method = RequestMethod.GET, value = "/cart")
	public ModelAndView items() {
		ModelAndView modelAndView = new ModelAndView("shoppingCart/items");
		modelAndView.addObject("listadejogos", new Game());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkout")
	public Callable<String> checkout(String currency) {
		return () -> {
			Payment urlPayPal = this.payPal.apiRequest(currency, shoppingCart);
			PurchaseService.registerPurchase(shoppingCart, userWeb, purchaseDao);
			return urlPayPal.getLinks().get(1).getHref();
		};
	}

	public String teste() {
		return new LicenseGenerator().generateLicense();
	}

	private final class LicenseGenerator {

		private LicenseGenerator() {
		}

		private String generateLicense() {
			return UUID.randomUUID().toString();
		}
	}

}
