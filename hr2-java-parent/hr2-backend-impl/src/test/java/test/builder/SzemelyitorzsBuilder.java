package test.builder;

import hu.interconnect.hr.domain.Csalad;
import hu.interconnect.hr.domain.JogviszonyAdatok;
import hu.interconnect.hr.domain.MunkakoriBesorolas;
import hu.interconnect.hr.domain.OrvosiVizsgalat;
import hu.interconnect.hr.domain.Szabadsagnyilvantartas;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class SzemelyitorzsBuilder extends Builder<Szemelyitorzs> {

	private Integer tsz;
	
	private SzemelyiAdatok szemelyiAdatok;
	
	private MunkakoriBesorolas munkakoriBesorolas;
	
	private JogviszonyAdatok jogviszonyAdatok;
	
	private Csalad csalad;
	
	private OrvosiVizsgalat orvosiVizsgalat;
	
	private Szabadsagnyilvantartas szabadsagnyilvantartas;
	
	public SzemelyitorzsBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public SzemelyitorzsBuilder szemelyiAdatok(SzemelyiAdatok szemelyiAdatok) {
		this.szemelyiAdatok = szemelyiAdatok;
		return this;
	}
	
	public SzemelyitorzsBuilder munkakoriBesorolas(MunkakoriBesorolas munkakoriBesorolas) {
		this.munkakoriBesorolas = munkakoriBesorolas;
		return this;
	}
	
	public SzemelyitorzsBuilder jogviszonyAdatok(JogviszonyAdatok jogviszonyAdatok) {
		this.jogviszonyAdatok = jogviszonyAdatok;
		return this;
	}
	
	public SzemelyitorzsBuilder orvosiVizsgalat(OrvosiVizsgalat orvosiVizsgalat) {
		this.orvosiVizsgalat = orvosiVizsgalat;
		return this;
	}
	
	public SzemelyitorzsBuilder szabadsagNyilvantartas(Szabadsagnyilvantartas szabadsagnyilvantartas) {
		this.szabadsagnyilvantartas = szabadsagnyilvantartas;
		return this;
	}
	
	@Override
	public Szemelyitorzs letrehoz() {
		return new Szemelyitorzs(tsz, szemelyiAdatok, munkakoriBesorolas, jogviszonyAdatok, csalad, orvosiVizsgalat, szabadsagnyilvantartas);
	}
}
