package test.builder;

import java.math.BigDecimal;

import hu.interconnect.hr.backend.api.enumeration.MunkakorJellege;
import hu.interconnect.hr.domain.FEOR;
import hu.interconnect.hr.domain.FoglalkozasiViszony;
import hu.interconnect.hr.domain.FoglalkoztatasJellege;
import hu.interconnect.hr.domain.Koltseghely;
import hu.interconnect.hr.domain.Munkakor;
import hu.interconnect.hr.domain.MunkakoriBesorolas;
import hu.interconnect.hr.domain.SzervezetiEgyseg;

public class MunkakoriBesorolasBuilder extends Builder<MunkakoriBesorolas>  {

	private SzervezetiEgyseg szervezetiEgyseg;
	
	private MunkakorJellege munkakorJellege;
	
	private Koltseghely koltseghely;
	
	private FoglalkozasiViszony foglalkozasiViszony;
	
	private FoglalkoztatasJellege foglalkoztatasJellege;
	
	private FEOR fEOR;
	
	private Munkakor munkakor;
	
	private boolean uzemanyagElszamolas;
	
	private BigDecimal munkaidoNapi;
	
	private BigDecimal munkaidoHeti;
	
	public MunkakoriBesorolasBuilder koltseghely(Koltseghely koltseghely) {
		this.koltseghely = koltseghely;
		return this;
	}

	public MunkakoriBesorolasBuilder munkakor(Munkakor munkakor) {
		this.munkakor = munkakor;
		return this;
	}
	
	public MunkakoriBesorolasBuilder uzemanyagElszamolas(boolean uzemanyagElszamolas) {
		this.uzemanyagElszamolas = uzemanyagElszamolas;
		return this;
	}
	
	@Override
	public MunkakoriBesorolas letrehoz() {
		return new MunkakoriBesorolas(szervezetiEgyseg, munkakorJellege, koltseghely, foglalkozasiViszony, foglalkoztatasJellege, fEOR, munkakor, uzemanyagElszamolas, munkaidoNapi, munkaidoHeti);
	}
}
