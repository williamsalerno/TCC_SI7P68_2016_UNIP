package br.com.timetrialfactory.maestro.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.timetrialfactory.maestro.daos.PurchaseDAO;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.Purchase;
import br.com.timetrialfactory.maestro.models.ShoppingCart;
import br.com.timetrialfactory.maestro.models.enums.PurchaseSituationType;

@Service("purchaseService")
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDAO dao;

	@Override
	public void savePurchase(ShoppingCart cart, LoggedUser userWeb) {
		if (userWeb.isLogged() && cart != null) {
			Purchase purchase = new Purchase();
			for (int i = 0; i < cart.getItems().size(); i++) {
				purchase.setGame(cart.getItems().get(i).getGame());
				purchase.setPrice(cart.getItems().get(i).getGame().getPrice());
				purchase.setPurchaseDate(LocalDateTime.now());
				purchase.setUser(userWeb.getLoggedUser());
				purchase.setPurchaseSituation(PurchaseSituationType.PROCESSANDO);
				dao.savePurchase(purchase);
			}
		}
	}

	@Override
	public void updatePurchase(Purchase purchase) {
		if (purchase != null) {
			dao.updatePurchase(purchase);
		}
	}

	@Override
	public Purchase findById(Long id) {
		Purchase purchase = new Purchase();
		purchase = dao.findById(id);
		return purchase;
	}

}