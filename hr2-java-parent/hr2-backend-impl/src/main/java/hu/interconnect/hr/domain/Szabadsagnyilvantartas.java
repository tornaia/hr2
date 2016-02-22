package hu.interconnect.hr.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Szabadsagnyilvantartas {

	@Column(name="MEGVALTOTT_SZABADSAG")
	private int megvaltottSzabadsag;
	
	public Szabadsagnyilvantartas(int megvaltottSzabadsag) {
		this.megvaltottSzabadsag = megvaltottSzabadsag;
	}
	
	public void merge(Szabadsagnyilvantartas szabadsagnyilvantartas) {
		this.megvaltottSzabadsag = szabadsagnyilvantartas.megvaltottSzabadsag;
	}
	
	public int getMegvaltottSzabadsag() {
		return megvaltottSzabadsag;
	}

	@SuppressWarnings("unused")
	private Szabadsagnyilvantartas() {
	}
}
