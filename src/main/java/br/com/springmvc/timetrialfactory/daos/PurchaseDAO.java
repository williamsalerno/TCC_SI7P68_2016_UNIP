package br.com.springmvc.timetrialfactory.daos;

import br.com.springmvc.timetrialfactory.models.Purchase;

public interface PurchaseDAO {

	void savePurchase(Purchase purchase);

	void updatePurchase(Purchase purchase);

	Purchase findById(Long id);

}
