package br.com.timetrialfactory.maestro.dto;

public class LicenseDTO {

	private String code;

	private GameDTO game;

	private UserDTO user;

	private boolean checkedCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public boolean getCheckedCode() {
		return checkedCode;
	}

	public void setCheckedCode(boolean checkedCode) {
		this.checkedCode = checkedCode;
	}

}
