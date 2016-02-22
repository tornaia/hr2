package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static java.util.Objects.nonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatSaveDTO;

@Entity
@Table(name = "EVES_SZABADSAG_ADAT")
public class EvesSzabadsagAdat extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="SZEMELYITORZS")
	private Szemelyitorzs szemelyitorzs;
	
	@Column(name="EV")
	private int ev;
	
	@Column(name="ALAPSZABADSAG")
	private int alapszabadsag;
	
	@Column(name="GYERMEKEK_UTAN")
	private int gyermekekUtan;
	
	@Column(name="FIATALKORU")
	private int fiatalkoru;
	
	@Column(name="VAK_SZEMELY")
	private int vakSzemely;
	
	@Column(name="EGYEB_MUNKAKOR")
	private int egyebMunkakor;
	
	@Column(name="KT_KA_POTSZABADSAG")
	private int ktKaPotszabadsag;
	
	@Column(name="KT_KA_VEZETOI")
	private int ktKaVezetoi;
	
	@Column(name="EGYEB_CSOKKENTO")
	private int egyebCsokkento;
	
	@Column(name="TANULMANYI")
	private int tanulmanyi;
	
	@Column(name="MULT_EVI_SZABADSAG")
	private int multEviSzabadsag;
	
	@Column(name="BSZ_JARANDOSAG_ADOTT_EVI")
	public int bszJarandosagAdottEvi;

	public EvesSzabadsagAdat(Szemelyitorzs szemelyitorzs, int ev, int alapszabadsag, int gyermekekUtan, int fiatalkoru, int vakSzemely, int egyebMunkakor, int ktKaPotszabadsag, int ktKaVezetoi, int egyebCsokkento, int tanulmanyi, int multEviSzabadsag, int bszJarandosagAdottEvi) {
		checkArgument(nonNull(szemelyitorzs));
		checkArgument(nonNull(szemelyitorzs.getTsz()));
		
		this.szemelyitorzs = szemelyitorzs;
		this.ev = ev;
		this.alapszabadsag = alapszabadsag;
		this.gyermekekUtan = gyermekekUtan;
		this.fiatalkoru = fiatalkoru;
		this.vakSzemely = vakSzemely;
		this.egyebMunkakor = egyebMunkakor;
		this.ktKaPotszabadsag = ktKaPotszabadsag;
		this.ktKaVezetoi = ktKaVezetoi;
		this.egyebCsokkento = egyebCsokkento;
		this.tanulmanyi = tanulmanyi;
		this.multEviSzabadsag = multEviSzabadsag;
		this.bszJarandosagAdottEvi = bszJarandosagAdottEvi;
	}
	
	public void merge(EvesSzabadsagAdatSaveDTO dto) {
		checkArgument(equalsAndNotNull(id, dto.getId()));
		checkArgument(equalsAndNotNull(szemelyitorzs.getTsz(), dto.getTsz()));
		checkArgument(equalsAndNotNull(ev, dto.getEv()));
		
		this.alapszabadsag = dto.getAlapszabadsag();
		this.gyermekekUtan = dto.getGyermekekUtan();
		this.fiatalkoru = dto.getFiatalkoru();
		this.vakSzemely = dto.getVakSzemely();
		this.egyebMunkakor = dto.getEgyebMunkakor();
		this.ktKaPotszabadsag = dto.getKtKaPotszabadsag();
		this.ktKaVezetoi = dto.getKtKaVezetoi();
		this.egyebCsokkento = dto.getEgyebCsokkento();
		this.tanulmanyi = dto.getTanulmanyi();
		this.multEviSzabadsag = dto.getMultEviSzabadsag();
		this.bszJarandosagAdottEvi = dto.getBszJarandosagAdottEvi();
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}

	public int getEv() {
		return ev;
	}
	public int getAlapszabadsag() {
		return alapszabadsag;
	}

	public int getGyermekekUtan() {
		return gyermekekUtan;
	}

	public int getFiatalkoru() {
		return fiatalkoru;
	}

	public int getVakSzemely() {
		return vakSzemely;
	}

	public int getEgyebMunkakor() {
		return egyebMunkakor;
	}

	public int getKtKaPotszabadsag() {
		return ktKaPotszabadsag;
	}

	public int getKtKaVezetoi() {
		return ktKaVezetoi;
	}

	public int getEgyebCsokkento() {
		return egyebCsokkento;
	}

	public int getTanulmanyi() {
		return tanulmanyi;
	}

	public int getMultEviSzabadsag() {
		return multEviSzabadsag;
	}

	public int getBszJarandosagAdottEvi() {
		return bszJarandosagAdottEvi;
	}

	@SuppressWarnings("unused")
	private EvesSzabadsagAdat() {
	}
}
