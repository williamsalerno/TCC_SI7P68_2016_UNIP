package br.com.timetrialfactory.maestro.models;

import static java.lang.Boolean.FALSE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "licenses", catalog = "licenses")
public class License {

	private static final byte CODE_LENGTH = 36;

	@Id
	@NotNull
	@Size(min = CODE_LENGTH, max = CODE_LENGTH)
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	private String code;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "checked_code")
	private Boolean checkedCode = FALSE;

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
