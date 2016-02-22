package hu.interconnect.hr.module.reports.vacation;

import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.APA_SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.BETEGSZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.TANULMANYI_SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.TEMETESI_SZABADSAG;
import static hu.interconnect.util.DateUtils.getCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.dao.EvesSzabadsagAdatDAO;
import hu.interconnect.hr.dao.SzabadsagDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.EvesSzabadsagAdat;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class VacationRowCreator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private SzabadsagDAO szabadsagDAO;
	
	@Autowired
	private EvesSzabadsagAdatDAO evesSzabadsagAdatDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(Date honap) {
		List<Szemelyitorzs> szemelyitorzsek = szemelyitorzsDAO.findOsszes();
		return szemelyitorzsek
				.stream()
				.map(szemelyitorzs -> szemelyitorzsAndHonapToRow.apply(szemelyitorzs, honap))
				.collect(Collectors.toList());
	}
	
	private BiFunction<Szemelyitorzs, Date, Map<String, Object>> szemelyitorzsAndHonapToRow = new BiFunction<Szemelyitorzs, Date, Map<String, Object>>() {

		@Override
		public Map<String, Object> apply(Szemelyitorzs szemelyitorzs, Date honap) {
			NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
			sor.put("nev", szemelyitorzs.getTeljesNev());
			
			List<Szabadsag> szabadsagok = szabadsagDAO.findBySzemelyitorzsEsAdottHaviIlletveAzelottiDeCsakAdottEviSzabadsagok(szemelyitorzs, honap);
			String szabadsagSzabadsagok = new SzabadsagExcelKivettSzabadnapMezotSzabadsagokbolEloallito(SZABADSAG, honap).apply(szabadsagok);
			sor.put("szabadsag_szabadsagok", szabadsagSzabadsagok);
			String betegSzabadsagok = new SzabadsagExcelKivettSzabadnapMezotSzabadsagokbolEloallito(BETEGSZABADSAG, honap).apply(szabadsagok);
			sor.put("betegszabadsag_szabadsagok", betegSzabadsagok);
			String temetesiSzabadsagSzabadsagok = new SzabadsagExcelKivettSzabadnapMezotSzabadsagokbolEloallito(TEMETESI_SZABADSAG, honap).apply(szabadsagok);
			sor.put("temetesi_szabadsag_szabadsagok", temetesiSzabadsagSzabadsagok);
			String apaSzabadsagSzabadsagok = new SzabadsagExcelKivettSzabadnapMezotSzabadsagokbolEloallito(APA_SZABADSAG, honap).apply(szabadsagok);
			sor.put("apa_szabadsag_szabadsagok", apaSzabadsagSzabadsagok);
			String tanulmanyiSzabadsagSzabadsagok = new SzabadsagExcelKivettSzabadnapMezotSzabadsagokbolEloallito(TANULMANYI_SZABADSAG, honap).apply(szabadsagok);
			sor.put("tanulmanyi_szabadsag_szabadsagok", tanulmanyiSzabadsagSzabadsagok);
			
			Optional<EvesSzabadsagAdat> optionalEvesSzabadsagAdat = evesSzabadsagAdatDAO.findBySzemelyitorzsEsEv(szemelyitorzs.getTsz(), getCalendar(honap).get(Calendar.YEAR));
			
			if (optionalEvesSzabadsagAdat.isPresent()) {
				EvesSzabadsagAdat evesSzabadsagAdat = optionalEvesSzabadsagAdat.get();
				int alapszabadsag = evesSzabadsagAdat.getAlapszabadsag();
				int gyermekekUtan = evesSzabadsagAdat.getGyermekekUtan();
				int fiatalkoru = evesSzabadsagAdat.getFiatalkoru();
				int vakSzemely = evesSzabadsagAdat.getVakSzemely();
				int egyebMunkakor = evesSzabadsagAdat.getEgyebMunkakor();
				int ktKaPotszabadsag = evesSzabadsagAdat.getKtKaPotszabadsag();
				int ktKaVezetoi = evesSzabadsagAdat.getKtKaVezetoi();
				int egyebCsokkento = evesSzabadsagAdat.getEgyebCsokkento();
				int tanulmanyi = evesSzabadsagAdat.getTanulmanyi();
				int multEviSzabadsag = evesSzabadsagAdat.getMultEviSzabadsag();
	            int szabadsagNapOsszesen = alapszabadsag + gyermekekUtan + fiatalkoru + vakSzemely + egyebMunkakor + ktKaPotszabadsag + ktKaVezetoi - egyebCsokkento + tanulmanyi + multEviSzabadsag;
	            int felhasznaltSzabadsagEsTanulmanyiSzabadsagokSzama = szabadsagDAO.findBySzemelyitorzsEsIdopontElottiDeAdottEviCsakSzabadsagEsTanulmanyiSzabadsagok(szemelyitorzs, honap).size();
	            int maradekSzabadsagAzAdottHonapVege = szabadsagNapOsszesen - felhasznaltSzabadsagEsTanulmanyiSzabadsagokSzama;
	            sor.put("maradek_szabadsag_a_honap_vegen", maradekSzabadsagAzAdottHonapVege);
			} else {
				int felhasznaltSzabadsagEsTanulmanyiSzabadsagokSzama = szabadsagDAO.findBySzemelyitorzsEsIdopontElottiDeAdottEviCsakSzabadsagEsTanulmanyiSzabadsagok(szemelyitorzs, honap).size();
	            int maradekSzabadsagAzAdottHonapVege = 0 - felhasznaltSzabadsagEsTanulmanyiSzabadsagokSzama;
	            sor.put("maradek_szabadsag_a_honap_vegen", maradekSzabadsagAzAdottHonapVege);
			}
			
			return sor.getMap();
		}
	};
}
