package test.builder;

import hu.interconnect.hr.domain.Szabadsagnyilvantartas;

public class SzabadsagnyilvantartasBuilder extends Builder<Szabadsagnyilvantartas> {

	private int megvaltottSzabadsag;
	
	public SzabadsagnyilvantartasBuilder megvaltottSzabadsag(int megvaltottSzabadsag) {
		this.megvaltottSzabadsag = megvaltottSzabadsag;
		return this;
	}
	
	@Override
	public Szabadsagnyilvantartas letrehoz() {
		return new Szabadsagnyilvantartas(megvaltottSzabadsag);
	}
}
