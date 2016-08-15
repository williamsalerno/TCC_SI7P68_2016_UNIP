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
import com.paypal.base.rest.PayPalRESTException;

import br.com.springmvc.timetrialfactory.apis.paypal.PayPalCall;
import br.com.springmvc.timetrialfactory.daos.PurchaseDAO;
import br.com.springmvc.timetrialfactory.models.Game;
import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;
import br.com.springmvc.timetrialfactory.models.ShoppingItem;
import br.com.springmvc.timetrialfactory.services.GameService;
import br.com.springmvc.timetrialfactory.services.PurchaseService;

@Controller
@RequestMapping("/shopping/cart")
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

	@RequestMapping(method = RequestMethod.POST, value = "/addGame")
	public ModelAndView add(final Long gameId) {
		ShoppingItem item = createItem(gameId);
		shoppingCart.add(item);
		ModelAndView modelAndView = new ModelAndView("redirect:/shopping/cart/view");
		modelAndView.addObject("shoppingCart", shoppingCart);
		return modelAndView;
	}

	private ShoppingItem createItem(final Long id) {
		Game game = service.findGameById(id);
		ShoppingItem item = new ShoppingItem(game);
		return item;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view")
	public ModelAndView items() {
		ModelAndView modelAndView = new ModelAndView("shoppingCart/items");
		modelAndView.addObject("listadejogos", new Game());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkout")
	public String checkout(final String currency) throws PayPalRESTException {
		Payment urlPayPal = this.payPal.apiRequest(currency, shoppingCart);
		PurchaseService.registerPurchase(shoppingCart, userWeb, purchaseDao);
		return urlPayPal.getLinks().get(1).getHref();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView remove(final int gameId) {
		shoppingCart.remove(gameId);
		return new ModelAndView("redirect:/shopping/cart/view");
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
