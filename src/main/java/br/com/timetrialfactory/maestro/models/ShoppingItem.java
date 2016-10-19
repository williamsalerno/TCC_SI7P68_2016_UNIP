package br.com.timetrialfactory.maestro.models;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.GameDTO;

@Component
@Scope(value = SCOPE_SESSION)
public class ShoppingItem {

	@Autowired
	private GameDTO game;

	public ShoppingItem() {
	}

	public ShoppingItem(GameDTO game) {
		this.game = game;
	}

	public GameDTO getGame() {
		return game;
	}

	public void setGame(GameDTO game) {
		this.game = game;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ShoppingItem)) {
			return false;
		}
		ShoppingItem outra = (ShoppingItem) obj;
		return new EqualsBuilder().append(this.game, outra.game).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.game).toHashCode();
	}

}
