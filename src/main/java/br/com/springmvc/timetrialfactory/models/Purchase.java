package br.com.springmvc.timetrialfactory.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.springmvc.timetrialfactory.models.enums.PurchaseSituationType;

@Entity
@Table(name = "purchases", catalog = "purchases")
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "game_id")
	@Id
	private Game game;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@Id
	private User user;

	@NotNull
	@Column(name = "game_price", nullable = false)
	private BigDecimal price;

	@NotNull
	@Column(name = "purchase_date", nullable = false)
	private LocalDateTime purchaseDate;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "purchase_situation", nullable = false)
	private PurchaseSituationType purchaseSituation;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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

	public PurchaseSituationType getPurchaseSituation() {
		return purchaseSituation;
	}

	public void setPurchaseSituation(PurchaseSituationType purchaseSituation) {
		this.purchaseSituation = purchaseSituation;
	}

}
