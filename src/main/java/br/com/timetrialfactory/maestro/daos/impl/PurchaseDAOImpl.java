package br.com.timetrialfactory.maestro.daos.impl;

import org.springframework.stereotype.Repository;

import br.com.timetrialfactory.maestro.daos.AbstractDAO;
import br.com.timetrialfactory.maestro.daos.PurchaseDAO;
import br.com.timetrialfactory.maestro.models.Purchase;

@Repository("purchaseDao")
public class PurchaseDAOImpl extends AbstractDAO<Long, Purchase> implements PurchaseDAO {

	@Override
	public void savePurchase(Purchase purchase) {
		persist(purchase);
	}

	@Override
	public void updatePurchase(Purchase purchase) {
		update(purchase);
	}

	@Override
	public Purchase findById(Long id) {
		return getByKey(id);
	}

}
