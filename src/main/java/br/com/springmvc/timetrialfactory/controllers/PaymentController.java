package br.com.springmvc.timetrialfactory.controllers;

import static br.com.springmvc.timetrialfactory.controllers.LicenseGenerator.generateLicense;

import java.util.UUID;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.api.payments.Payment;

import br.com.springmvc.timetrialfactory.apis.paypal.PayPalCall;
import br.com.springmvc.timetrialfactory.daos.PurchaseDAO;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;
import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.services.PurchaseService;

@Controller
@Transactional
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private PayPalCall payPal;

	@Autowired
	private PurchaseDAO purchaseDao;

	@Autowired
	private LoggedUser userWeb;

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public Callable<String> checkout(String currency) {
		return () -> {
			Payment urlPayPal = this.payPal.apiRequest(currency, shoppingCart);
			PurchaseService.registerPurchase(shoppingCart, userWeb, purchaseDao);
			return urlPayPal.getLinks().get(1).getHref();
		};
	}

	public String teste() {
		return generateLicense();
	}

}

class LicenseGenerator {

	private LicenseGenerator() {
	}

	static String generateLicense() {
		return UUID.randomUUID().toString();
	}
}
