package test.builder;

import static hu.interconnect.util.DateUtils.parseEv;
import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.domain.Kepzettseg;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class KepzettsegBuilder extends Builder<Kepzettseg> {

	private Szemelyitorzs szemelyitorzs;
	
	private String tipus;
	
	private String megnevezes;
	
	private String modFok;
	
	private Date ev;

	private Date ervenyessegVege;

	private String megjegyzes;
	
	public KepzettsegBuilder szemelyitorzs(Szemelyitorzs szemelyitorzs) {
		this.szemelyitorzs = szemelyitorzs;
		return this;
	}
	
	public KepzettsegBuilder tipus(String tipus) {
		this.tipus = tipus;
		return this;
	}
	
	public KepzettsegBuilder megnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
		return this;
	}
	
	public KepzettsegBuilder modFok(String modFok) {
		this.modFok = modFok;
		return this;
	}
	
	public KepzettsegBuilder ev(String ev) {
		this.ev = parseEv(ev);
		return this;
	}
	
	public KepzettsegBuilder ervenyessegVege(String napStr) {
		return ervenyessegVege(parseNap(napStr));
	}
	
	public KepzettsegBuilder ervenyessegVege(Date datum) {
		this.ervenyessegVege = datum;
		return this;
	}
	
	public KepzettsegBuilder megjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
		return this;
	}
	
	@Override
	public Kepzettseg letrehoz() {
		return new Kepzettseg(szemelyitorzs, tipus, megnevezes, modFok, ev, ervenyessegVege, megjegyzes);
	}
}
