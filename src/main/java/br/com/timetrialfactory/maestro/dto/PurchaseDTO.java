package br.com.timetrialfactory.maestro.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PurchaseDTO {
	
	private GameDTO game;
	
	private UserDTO user;
	
	private BigDecimal price;
	
	private LocalDateTime purchaseDate;
	
	private String purchaseSituation;

	public GameDTO getGame() {
		return game;
	}

	public void setGame(GameDTO game) {
		this.game = game;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseSituation() {
		return purchaseSituation;
	}

	public void setPurchaseSituation(String purchaseSituation) {
		this.purchaseSituation = purchaseSituation;
	}
	
}
