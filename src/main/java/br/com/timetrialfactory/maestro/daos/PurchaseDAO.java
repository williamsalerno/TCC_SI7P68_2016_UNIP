package br.com.timetrialfactory.maestro.daos;

import br.com.timetrialfactory.maestro.models.Purchase;

public interface PurchaseDAO {

	void savePurchase(Purchase purchase);

	void updatePurchase(Purchase purchase);

	Purchase findById(Long id);

}
