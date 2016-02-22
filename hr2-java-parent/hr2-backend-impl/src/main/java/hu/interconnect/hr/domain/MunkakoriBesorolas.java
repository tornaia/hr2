package hu.interconnect.hr.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.common.collect.Sets;

import hu.interconnect.hr.backend.api.enumeration.MunkakorJellege;

@Embeddable
public class MunkakoriBesorolas {

	@ManyToOne
	@JoinColumn(name="SZERVEZETI_EGYSEG")
	private SzervezetiEgyseg szervezetiEgyseg;
	
	@Column(name="MUNKAKOR_JELLEGE")
	@Enumerated(EnumType.STRING)
	private MunkakorJellege munkakorJellege;
	
	@ManyToOne
	@JoinColumn(name="KOLTSEGHELY")
	private Koltseghely koltseghely;
	
	@ManyToOne
	@JoinColumn(name="FOGLALKOZASI_VISZONY")
	private FoglalkozasiViszony foglalkozasiViszony;
	
	@ManyToOne
	@JoinColumn(name="FOGLALKOZTATAS_JELLEGE")
	private FoglalkoztatasJellege foglalkoztatasJellege;
	
	@ManyToOne
	@JoinColumn(name="FEOR")
	private FEOR fEOR;
	
	@ManyToOne
	@JoinColumn(name="MUNKAKOR")
	private Munkakor munkakor;
	
	@Column(name="UZEMANYAG_ELSZAMOLAS")
	private boolean uzemanyagElszamolas;
	
	@Column(name="MUNKAIDO_NAPI")
	private BigDecimal munkaidoNapi;
	
	@Column(name="MUNKAIDO_HETI")
	private BigDecimal munkaidoHeti;
	
	@OneToMany(mappedBy="szemelyitorzs", cascade={CascadeType.REMOVE})
	private Set<MunkakoriHistorikusAdat> munkakoriHistorikusAdatok;
	
	public MunkakoriBesorolas(SzervezetiEgyseg szervezetiEgyseg, MunkakorJellege munkakorJellege, Koltseghely koltseghely, FoglalkozasiViszony foglalkozasiViszony, FoglalkoztatasJellege foglalkoztatasJellege, FEOR fEOR, Munkakor munkakor, boolean uzemanyagElszamolas, BigDecimal munkaidoNapi, BigDecimal munkaidoHeti) {
		this.szervezetiEgyseg = szervezetiEgyseg;
		this.munkakorJellege = munkakorJellege;
		this.koltseghely = koltseghely;
		this.foglalkozasiViszony = foglalkozasiViszony;
		this.foglalkoztatasJellege = foglalkoztatasJellege;
		this.fEOR = fEOR;
		this.munkakor = munkakor;
		this.uzemanyagElszamolas = uzemanyagElszamolas;
		this.munkaidoNapi = munkaidoNapi;
		this.munkaidoHeti = munkaidoHeti;
		this.munkakoriHistorikusAdatok = munkakoriHistorikusAdatok == null ? Sets.<MunkakoriHistorikusAdat>newHashSet() : Sets.<MunkakoriHistorikusAdat>newHashSet(munkakoriHistorikusAdatok);
	}

	public SzervezetiEgyseg getSzervezetiEgyseg() {
		return szervezetiEgyseg;
	}

	public MunkakorJellege getMunkakorJellege() {
		return munkakorJellege;
	}

	public Koltseghely getKoltseghely() {
		return koltseghely;
	}

	public FoglalkozasiViszony getFoglalkozasiViszony() {
		return foglalkozasiViszony;
	}

	public FoglalkoztatasJellege getFoglalkoztatasJellege() {
		return foglalkoztatasJellege;
	}

	public FEOR getfEOR() {
		return fEOR;
	}

	public Munkakor getMunkakor() {
		return munkakor;
	}

	public boolean getUzemanyagElszamolas() {
		return uzemanyagElszamolas;
	}

	public BigDecimal getMunkaidoNapi() {
		return munkaidoNapi;
	}

	public BigDecimal getMunkaidoHeti() {
		return munkaidoHeti;
	}

	public Set<MunkakoriHistorikusAdat> getMunkakoriHistorikusAdatok() {
		return munkakoriHistorikusAdatok;
	}

	@SuppressWarnings("unused")
	private MunkakoriBesorolas() {
	}
}
