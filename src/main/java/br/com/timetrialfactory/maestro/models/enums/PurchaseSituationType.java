package br.com.timetrialfactory.maestro.models.enums;

public enum PurchaseSituationType {

	FINALIZADO("FINALIZADO", 1), PROCESSANDO("PROCESSANDO", 2), CANCELADO("CANCELADO", 3);

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
