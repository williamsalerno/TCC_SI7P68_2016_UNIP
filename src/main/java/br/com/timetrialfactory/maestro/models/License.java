package br.com.timetrialfactory.maestro.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "licenses", catalog = "licenses")
public class License {

	@Id
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	@NotNull
	private String code;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "checked_code")
	private Boolean checkedCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public Boolean getCheckedCode() {
		return checkedCode;
	}

	public void setCheckedCode(Boolean checkedCode) {
		this.checkedCode = checkedCode;
	}

}
