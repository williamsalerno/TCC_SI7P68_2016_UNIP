package br.com.springmvc.timetrialfactory.dto;

public class LicenseDTO {

	private String code;

	private long userId;

	private long gameId;

	private boolean checkedCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public boolean isCheckedCode() {
		return checkedCode;
	}

	public void setCheckedCode(boolean checkedCode) {
		this.checkedCode = checkedCode;
	}

}
