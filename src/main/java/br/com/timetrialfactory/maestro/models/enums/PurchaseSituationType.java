package br.com.timetrialfactory.maestro.models.enums;

public enum PurchaseSituationType {

	PENDENTE("PENDENTE", 1), PROCESSANDO("PROCESSANDO", 2), CONFIRMADO("CONFIRMADO", 3), CANCELADO("CANCELADO", 4);

	private String situacao;
	private Integer id;

	private PurchaseSituationType(String situacao, Integer id) {

	}

	public String getSituacao() {
		return situacao;
	}

	public Integer getId() {
		return id;
	}
}
