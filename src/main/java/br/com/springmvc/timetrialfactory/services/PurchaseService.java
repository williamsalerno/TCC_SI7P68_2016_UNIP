package br.com.springmvc.timetrialfactory.services;

import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.models.Purchase;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;

public interface PurchaseService {

	void savePurchase(ShoppingCart cart, LoggedUser userWeb);

	void updatePurchase(Purchase purchase);

	Purchase findById(Long id);

}
