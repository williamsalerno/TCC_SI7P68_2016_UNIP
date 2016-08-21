package br.com.springmvc.timetrialfactory.daos.impl;

import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.daos.AbstractDAO;
import br.com.springmvc.timetrialfactory.daos.PurchaseDAO;
import br.com.springmvc.timetrialfactory.models.Purchase;

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
