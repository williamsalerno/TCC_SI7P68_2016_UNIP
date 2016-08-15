package br.com.springmvc.timetrialfactory.services;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import br.com.springmvc.timetrialfactory.daos.PurchaseDAO;
import br.com.springmvc.timetrialfactory.models.Purchase;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;
import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.models.enums.PurchaseSituationType;

public class PurchaseService {

	private PurchaseService() {
	}

	public static void registerPurchase(ShoppingCart cart, LoggedUser userWeb, PurchaseDAO dao) {
		Purchase purchase = new Purchase();
		for (int i = 0; i < cart.getItems().size(); i++) {
			purchase.setGame(cart.getItems().get(i).getGame());
			purchase.setPrice(cart.getItems().get(i).getGame().getPrice());
			purchase.setPurchaseDate(LocalDateTime.now(DateTimeZone.forID("America/Sao_Paulo")));
			purchase.setUser(userWeb.getLoggedUser());
			purchase.setPurchaseSituation(PurchaseSituationType.PROCESSANDO);
			//dao.save(purchase);
		}
	}

}
