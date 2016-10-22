package br.com.timetrialfactory.maestro.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.PurchaseDTO;
import br.com.timetrialfactory.maestro.models.Purchase;
import br.com.timetrialfactory.maestro.models.enums.PurchaseSituationType;

@Component
public class PurchaseAssembler implements Assembler<PurchaseDTO, Purchase> {

	@Autowired
	private GameAssembler gameAssembler;

	@Autowired
	private UserAssembler userAssembler;

	@Override
	public PurchaseDTO toObject(Purchase entity) {
		PurchaseDTO object = null;
		if (entity != null) {
			object = new PurchaseDTO();
			object.setGame(gameAssembler.toObject(entity.getGame()));
			object.setUser(userAssembler.toObject(entity.getUser()));
			object.setPrice(entity.getPrice());
			object.setPurchaseDate(entity.getPurchaseDate());
			object.setPurchaseSituation(entity.getPurchaseSituation().getSituacao());
		}
		return object;
	}

	@Override
	public Purchase toEntity(PurchaseDTO object) {
		Purchase entity = null;
		if (object != null) {
			entity = new Purchase();
			entity.setGame(gameAssembler.toEntity(object.getGame()));
			entity.setUser(userAssembler.toEntity(object.getUser()));
			entity.setPrice(object.getPrice());
			entity.setPurchaseDate(object.getPurchaseDate());
			entity.setPurchaseSituation(PurchaseSituationType.valueOf(object.getPurchaseSituation()));
		}
		return entity;
	}

}
