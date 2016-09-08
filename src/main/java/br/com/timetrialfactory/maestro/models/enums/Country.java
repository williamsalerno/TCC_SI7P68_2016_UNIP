package br.com.timetrialfactory.maestro.models.enums;

public enum Country {
	

	BRAZIL("BRAZIL"), OTHER("OTHER");

	private Country(String name) {
	}

	public String getName() {
		return this.name();
	}

}
