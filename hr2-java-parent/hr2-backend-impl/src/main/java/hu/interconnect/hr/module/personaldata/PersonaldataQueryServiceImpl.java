package hu.interconnect.hr.module.personaldata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO.CsaladDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO.JogviszonyAdatokDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO.MunkakoriBesorolasDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO.OrvosiVizsgalatDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO.SzabadsagnyilvantartasDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO.SzemelyiAdatokDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO.SzemelyiAdatokDTO.LakcimDTO;
import hu.interconnect.hr.backend.api.dto.TszekEsNevekDTO;
import hu.interconnect.hr.backend.api.service.PersonaldataQueryService;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Csalad;
import hu.interconnect.hr.domain.JogviszonyAdatok;
import hu.interconnect.hr.domain.Lakcim;
import hu.interconnect.hr.domain.MunkakoriBesorolas;
import hu.interconnect.hr.domain.OrvosiVizsgalat;
import hu.interconnect.hr.domain.Szabadsagnyilvantartas;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class PersonaldataQueryServiceImpl implements PersonaldataQueryService {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public SzemelyitorzsResponseDTO getSzemelyitorzs(int tsz) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findByIdFetchelve(tsz);
		return toDTO(szemelyitorzs);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Integer> getOsszesSzemelyitorzs() {
		return szemelyitorzsDAO.findOsszesSzemelyitorzsTsz();
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Integer> getOsszesAktivSzemelyitorzs() {
		return szemelyitorzsDAO.findOsszesAktivSzemelyitorzsTsz();
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public TszekEsNevekDTO getIdAndNameList(boolean all) {
		if (all) {
			return new TszekEsNevekDTO(szemelyitorzsDAO.getOsszesSzemelyitorzsTszEsNevListaban());
		} else {
			return new TszekEsNevekDTO(szemelyitorzsDAO.getOsszesAktivSzemelyitorzsTszEsNevListaban());
		}
	}
	
	private static SzemelyitorzsResponseDTO toDTO(Szemelyitorzs szemelyitorzs) {
		SzemelyitorzsResponseDTO.SzemelyiAdatokDTO szemelyiAdatok = toDTO(szemelyitorzs.getSzemelyiAdatok());
		MunkakoriBesorolasDTO munkakoriBesorolas = toDTO(szemelyitorzs.getMunkakoriBesorolas());
		JogviszonyAdatokDTO jogviszonyAdatok = toDTO(szemelyitorzs.getJogviszonyAdatok());
		CsaladDTO csalad = toDTO(szemelyitorzs.getCsalad());
		OrvosiVizsgalatDTO orvosiVizsgalat = toDTO(szemelyitorzs.getOrvosiVizsgalat());
		SzabadsagnyilvantartasDTO szabadsagnyilvantartas = toDTO(szemelyitorzs.getSzabadsagnyilvantartas());
		return new SzemelyitorzsResponseDTO(szemelyitorzs.getTsz(), szemelyiAdatok, munkakoriBesorolas, jogviszonyAdatok, csalad, orvosiVizsgalat, szabadsagnyilvantartas);
	}

	private static SzemelyiAdatokDTO toDTO(SzemelyiAdatok szemelyiAdatok) {
		if (szemelyiAdatok == null) {
			return null;
		}
		LakcimDTO lakcimAllando = toDTO(szemelyiAdatok.getLakcimAllando());
		LakcimDTO lakcimIdeiglenes = toDTO(szemelyiAdatok.getLakcimIdeiglenes());
		Integer allampolgarsag = szemelyiAdatok.getAllampolgarsag() != null ? szemelyiAdatok.getAllampolgarsag().getId() : null;
		return new SzemelyiAdatokDTO(szemelyiAdatok.getVezeteknev(), szemelyiAdatok.getKeresztnev(), szemelyiAdatok.getNem(), allampolgarsag, szemelyiAdatok.getSzuletesiDatum(), szemelyiAdatok.getSzuletesiHely(), szemelyiAdatok.getSzuletesiOrszag(), szemelyiAdatok.getSzuletesiNev(), szemelyiAdatok.getSzuletesiNevAnyja(), szemelyiAdatok.getAdoazonositoJel(), szemelyiAdatok.getTaj(), szemelyiAdatok.getSzemelyiIgazolvanySzam(), szemelyiAdatok.getSzemelyiIgazolvanySzamLejarat(), szemelyiAdatok.getUtlevelSzam(), szemelyiAdatok.getUtlevelSzamLejarat(), szemelyiAdatok.getJogositvanySzam(), szemelyiAdatok.getJogositvanySzamLejarat(), szemelyiAdatok.getTelefon(), szemelyiAdatok.getMobil(), szemelyiAdatok.getEmail(), szemelyiAdatok.getLakcimAktualis(), lakcimAllando, lakcimIdeiglenes);
	}

	private static LakcimDTO toDTO(Lakcim lakcim) {
		return lakcim != null ? new LakcimDTO(lakcim.getIranyitoszam(), lakcim.getTelepules(), lakcim.getKerulet(), lakcim.getKozteruletNev(), lakcim.getKozteruletTipus(), lakcim.getKozteruletSzam(), lakcim.getEpulet(), lakcim.getLepcsohaz(), lakcim.getEmelet(), lakcim.getAjto()) : null;
	}
	
	private static MunkakoriBesorolasDTO toDTO(MunkakoriBesorolas munkakoriBesorolas) {
		if (munkakoriBesorolas == null) {
			return null;
		}
		Integer szervezetiEgyseg = munkakoriBesorolas.getSzervezetiEgyseg() != null ? munkakoriBesorolas.getSzervezetiEgyseg().getId() : null;
		Integer koltseghely = munkakoriBesorolas.getKoltseghely() != null ? munkakoriBesorolas.getKoltseghely().getId() : null;
		Integer foglalkozasiViszony = munkakoriBesorolas.getFoglalkozasiViszony() != null ? munkakoriBesorolas.getFoglalkozasiViszony().getId() : null;
		Integer foglalkoztatasJellege = munkakoriBesorolas.getFoglalkoztatasJellege() != null ? munkakoriBesorolas.getFoglalkoztatasJellege().getId() : null;
		Integer fEOR = munkakoriBesorolas.getfEOR() != null ? munkakoriBesorolas.getfEOR().getId() : null;
		Integer munkakor = munkakoriBesorolas.getMunkakor() != null ? munkakoriBesorolas.getMunkakor().getId() : null;
		return new MunkakoriBesorolasDTO(szervezetiEgyseg, munkakoriBesorolas.getMunkakorJellege(), koltseghely, foglalkozasiViszony, foglalkoztatasJellege, fEOR, munkakor, munkakoriBesorolas.getUzemanyagElszamolas(), munkakoriBesorolas.getMunkaidoNapi(), munkakoriBesorolas.getMunkaidoHeti());
	}

	private static JogviszonyAdatokDTO toDTO(JogviszonyAdatok jogviszonyAdatok) {
		return jogviszonyAdatok != null ? new JogviszonyAdatokDTO(jogviszonyAdatok.getJogviszonyKezdete(), jogviszonyAdatok.getJogviszonyVege(), jogviszonyAdatok.getJogviszonyMegszunesenekModja(), jogviszonyAdatok.getProbaidoVege(), jogviszonyAdatok.getMunkaszerzodesLejar(), jogviszonyAdatok.getAllomanymod()) : null;
	}
	
	private static CsaladDTO toDTO(Csalad csalad) {
		return csalad != null ? new CsaladDTO(csalad.getCsaladiAllapot()) : null;
	}
	
	private static OrvosiVizsgalatDTO toDTO(OrvosiVizsgalat orvosiVizsgalat) {
		return orvosiVizsgalat != null ? new OrvosiVizsgalatDTO(orvosiVizsgalat.getGyakorisag(), orvosiVizsgalat.getUtolsoOrvosiVizsgalatIdopontja()) : null;
	}
	
	private static SzabadsagnyilvantartasDTO toDTO(Szabadsagnyilvantartas szabadsagnyilvantartas) {
		return szabadsagnyilvantartas != null ? new SzabadsagnyilvantartasDTO(szabadsagnyilvantartas.getMegvaltottSzabadsag()) : null;
	}
}
