package br.com.timetrialfactory.maestro.services;

import br.com.timetrialfactory.maestro.dto.PurchaseDTO;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.ShoppingCart;

public interface PurchaseService {

	void savePurchase(ShoppingCart cart, LoggedUser userWeb);

	void updatePurchase(PurchaseDTO purchase);

	PurchaseDTO findById(Long id);

}
