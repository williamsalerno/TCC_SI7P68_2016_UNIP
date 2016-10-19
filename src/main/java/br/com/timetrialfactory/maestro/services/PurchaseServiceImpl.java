package br.com.timetrialfactory.maestro.services;

import static java.time.LocalDateTime.now;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.timetrialfactory.maestro.assembler.GameAssembler;
import br.com.timetrialfactory.maestro.daos.PurchaseDAO;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.Purchase;
import br.com.timetrialfactory.maestro.models.ShoppingCart;
import br.com.timetrialfactory.maestro.models.ShoppingItem;
import br.com.timetrialfactory.maestro.models.enums.PurchaseSituationType;

@Service("purchaseService")
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

	private final static BigDecimal free = new BigDecimal("0.00");

	@Autowired
	private PurchaseDAO dao;
	
	@Autowired
	private GameAssembler gameAssembler;

	@Override
	public void savePurchase(ShoppingCart cart, LoggedUser userWeb) {
		if (userWeb.isLogged() && cart != null) {
			Purchase purchase = new Purchase();
			for (ShoppingItem items : cart.getItems()) {
				purchase.setGame(gameAssembler.toEntity(items.getGame()));
				if (items.getGame() != null) {
					purchase.setPrice(items.getGame().getPrice());
				}
				purchase.setPurchaseDate(now());
				purchase.setUser(userWeb.getLoggedUser());
				if (purchase.getPrice() != null) {
					if (purchase.getPrice().equals(free)) {
						purchase.setPurchaseSituation(PurchaseSituationType.CONFIRMADO);
					} else {
						purchase.setPurchaseSituation(PurchaseSituationType.PROCESSANDO);
					}
				}
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
