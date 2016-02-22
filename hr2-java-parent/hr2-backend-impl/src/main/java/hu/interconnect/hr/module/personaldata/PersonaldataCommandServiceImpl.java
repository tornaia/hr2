package hu.interconnect.hr.module.personaldata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.CsaladDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.JogviszonyAdatokDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.MunkakoriBesorolasDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.OrvosiVizsgalatDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.SzabadsagnyilvantartasDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.SzemelyiAdatokDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.SzemelyiAdatokDTO.LakcimDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO;
import hu.interconnect.hr.backend.api.service.PersonaldataCommandService;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Allampolgarsag;
import hu.interconnect.hr.domain.Csalad;
import hu.interconnect.hr.domain.FEOR;
import hu.interconnect.hr.domain.FoglalkozasiViszony;
import hu.interconnect.hr.domain.FoglalkoztatasJellege;
import hu.interconnect.hr.domain.JogviszonyAdatok;
import hu.interconnect.hr.domain.Koltseghely;
import hu.interconnect.hr.domain.Lakcim;
import hu.interconnect.hr.domain.Munkakor;
import hu.interconnect.hr.domain.MunkakoriBesorolas;
import hu.interconnect.hr.domain.OrvosiVizsgalat;
import hu.interconnect.hr.domain.Szabadsagnyilvantartas;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.domain.SzervezetiEgyseg;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class PersonaldataCommandServiceImpl implements PersonaldataCommandService {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;

	@Autowired
	private PersonaldataCreateValidator personaldataCreateValidator;

	@Autowired
	private PersonaldataDeleteValidator personaldataDeleteValidator;

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void letrehoz(SzemelyitorzsCreateDTO dto) {
		personaldataCreateValidator.validate(dto);
		Szemelyitorzs szemelyitorzs = toDomain(dto);
		szemelyitorzsDAO.persist(szemelyitorzs);
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void szerkeszt(SzemelyitorzsEditDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		szemelyitorzs.merge(dto);
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void torol(int tsz) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(tsz);
		personaldataDeleteValidator.validate(szemelyitorzs);
		szemelyitorzsDAO.delete(szemelyitorzs.getTsz());
	}

	private static Szemelyitorzs toDomain(SzemelyitorzsCreateDTO dto) {
		SzemelyiAdatok szemelyiAdatok = toDomain(dto.getSzemelyiAdatok());
		MunkakoriBesorolas munkakoriBesorolas = toDomain(dto.getMunkakoriBesorolas());
		JogviszonyAdatok jogviszonyAdatok = toDomain(dto.getJogviszonyAdatok());
		Csalad csalad = toDomain(dto.getCsalad());
		OrvosiVizsgalat orvosiVizsgalat = toDomain(dto.getOrvosiVizsgalat());
		Szabadsagnyilvantartas szabadsagnyilvantartas = toDomain(dto.getSzabadsagnyilvantartas());
		return new Szemelyitorzs(dto.getTsz(), szemelyiAdatok, munkakoriBesorolas, jogviszonyAdatok, csalad, orvosiVizsgalat, szabadsagnyilvantartas);
	}

	private static SzemelyiAdatok toDomain(SzemelyiAdatokDTO dto) {
		Allampolgarsag allampolgarsag = toAllampolgarsagDomain(dto.getAllampolgarsag());
		Lakcim lakcimAllando = toDomain(dto.getLakcimAllando());
		Lakcim lakcimIdeiglenes = toDomain(dto.getLakcimIdeiglenes());
		return new SzemelyiAdatok(dto.getVezeteknev(), dto.getKeresztnev(), dto.getNem(), allampolgarsag, dto.getSzuletesiDatum(), dto.getSzuletesiHely(), dto.getSzuletesiOrszag(), dto.getSzuletesiNev(), dto.getSzuletesiNevAnyja(), dto.getAdoazonositoJel(), dto.getTaj(), dto.getSzemelyiIgazolvanySzam(), dto.getSzemelyiIgazolvanySzamLejarat(), dto.getUtlevelSzam(), dto.getUtlevelSzamLejarat(), dto.getJogositvanySzam(), dto.getJogositvanySzamLejarat(), dto.getTelefon(), dto.getMobil(), dto.getEmail(), dto.getLakcimAktualis(), lakcimAllando, lakcimIdeiglenes);
	}

	private static MunkakoriBesorolas toDomain(MunkakoriBesorolasDTO dto) {
		SzervezetiEgyseg szervezetiEgyseg = toSzervezetiEgysegDomain(dto.getSzervezetiEgyseg());
		Koltseghely koltseghely = toKoltseghelyDomain(dto.getKoltseghely());
		FoglalkozasiViszony foglalkozasiViszony = toFoglalkozasiViszonyDomain(dto.getFoglalkozasiViszony());
		FoglalkoztatasJellege foglalkoztatasJellege = toFoglalkoztatasJellegeDomain(dto.getFoglalkoztatasJellege());
		FEOR fEOR = toFEORDomain(dto.getfEOR());
		Munkakor munkakor = toMunkakorDomain(dto.getMunkakor());
		return new MunkakoriBesorolas(szervezetiEgyseg, dto.getMunkakorJellege(), koltseghely, foglalkozasiViszony, foglalkoztatasJellege, fEOR, munkakor, dto.isUzemanyagElszamolas(), dto.getMunkaidoNapi(), dto.getMunkaidoHeti());
	}

	private static JogviszonyAdatok toDomain(JogviszonyAdatokDTO jogviszonyAdatok) {
		return new JogviszonyAdatok(jogviszonyAdatok.getJogviszonyKezdete(), jogviszonyAdatok.getJogviszonyVege(), jogviszonyAdatok.getJogviszonyMegszunesenekModja(), jogviszonyAdatok.getProbaidoVege(), jogviszonyAdatok.getMunkaszerzodesLejar(), jogviszonyAdatok.getAllomanymod());
	}

	private static Csalad toDomain(CsaladDTO csalad) {
		return new Csalad(csalad.getCsaladiAllapot());
	}

	private static OrvosiVizsgalat toDomain(OrvosiVizsgalatDTO orvosiVizsgalat) {
		return new OrvosiVizsgalat(orvosiVizsgalat.getGyakorisag(), orvosiVizsgalat.getUtolsoOrvosiVizsgalatIdopontja());
	}

	private static Szabadsagnyilvantartas toDomain(SzabadsagnyilvantartasDTO szabadsagnyilvantartas) {
		return new Szabadsagnyilvantartas(szabadsagnyilvantartas.getMegvaltottSzabadsag());
	}

	private static Lakcim toDomain(LakcimDTO lakcim) {
		return lakcim != null ? new Lakcim(lakcim.getIranyitoszam(), lakcim.getTelepules(), lakcim.getKerulet(), lakcim.getKozteruletNev(), lakcim.getKozteruletTipus(), lakcim.getKozteruletSzam(), lakcim.getEpulet(), lakcim.getLepcsohaz(), lakcim.getEmelet(), lakcim.getAjto()) : null;
	}

	private static Allampolgarsag toAllampolgarsagDomain(Integer id) {
		return id != null ? new Allampolgarsag(id) : null;
	}

	private static SzervezetiEgyseg toSzervezetiEgysegDomain(Integer id) {
		return id != null ? new SzervezetiEgyseg(id) : null;
	}

	private static Koltseghely toKoltseghelyDomain(Integer id) {
		return id != null ? new Koltseghely(id) : null;
	}

	private static FoglalkozasiViszony toFoglalkozasiViszonyDomain(Integer id) {
		return id != null ? new FoglalkozasiViszony(id) : null;
	}

	private static FoglalkoztatasJellege toFoglalkoztatasJellegeDomain(Integer id) {
		return id != null ? new FoglalkoztatasJellege(id) : null;
	}

	private static FEOR toFEORDomain(Integer id) {
		return id != null ? new FEOR(id) : null;
	}

	private static Munkakor toMunkakorDomain(Integer id) {
		return id != null ? new Munkakor(id) : null;
	}
}
