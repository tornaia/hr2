package test.builder;

import hu.interconnect.hr.domain.ErtekkeszletElem;

public abstract class ErtekkeszletElemBuilder<T extends ErtekkeszletElem> extends Builder<T> {

	protected String megnevezesMagyar;
	
	protected String megnevezesAngol;
	
	public ErtekkeszletElemBuilder<T> megnevezesMagyar(String megnevezesMagyar) {
		this.megnevezesMagyar = megnevezesMagyar;
		return this;
	}
	
	public ErtekkeszletElemBuilder<T> megnevezesAngol(String megnevezesAngol) {
		this.megnevezesAngol = megnevezesAngol;
		return this;
	}
}
