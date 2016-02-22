package hu.interconnect.hr.module.personaldata.vacations;

import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.APA_SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.BETEGSZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.TANULMANYI_SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.TEMETESI_SZABADSAG;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatGetDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO.ConsumptionTableDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO.ConsumptionTableDTO.ConsumptionPerTypePerMonthDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO.ConsumptionTableDTO.ConsumptionPerTypePerMonthDTO.AdottHaviEsAdottJelleguSzabadsagokDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasResponseDTO;
import hu.interconnect.hr.backend.api.service.VacationsQueryService;
import hu.interconnect.hr.dao.EvesSzabadsagAdatDAO;
import hu.interconnect.hr.dao.KivetelnapDAO;
import hu.interconnect.hr.dao.SzabadsagDAO;
import hu.interconnect.hr.domain.EvesSzabadsagAdat;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;
import hu.interconnect.hr.security.AuthorityConstants;
import hu.interconnect.util.DateIterator;
import hu.interconnect.util.Month;
import hu.interconnect.util.MonthIterator;

@Component
public class VacationsQueryServiceImpl implements VacationsQueryService {

	private static final int SZABADSAG_ORDER = 0;
	private static final int BETEGSZABADSAG_ORDER = 1;
	private static final int TEMETESI_SZABADSAG_ORDER = 2;
	private static final int APA_SZABADSAG_ORDER = 3;
	private static final int TANULMANYI_SZABADSAG_ORDER = 4;
	
	@Autowired
	private SzabadsagDAO szabadsagDAO;

	@Autowired
	private EvesSzabadsagAdatDAO evesSzabadsagAdatDAO;
	
	@Autowired
	private KivetelnapDAO kivetelnapDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public EvesSzabadsagAdatResponseDTO get(EvesSzabadsagAdatGetDTO dto) {
		List<Szabadsag> adottEviSzabadsagok = szabadsagDAO.findBySzemelyitorzsEsEv(dto.getTsz(), dto.getEv());
		KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(kivetelnapDAO.findAll());
		
		ConsumptionTableDTO consumptionTable = createConsumptionTableDTO(dto.getEv(), adottEviSzabadsagok, kivetelnapok);
		
		Optional<EvesSzabadsagAdat> optionalEvesSzabadsagAdat = evesSzabadsagAdatDAO.findBySzemelyitorzsEsEv(dto.getTsz(), dto.getEv());
		if (optionalEvesSzabadsagAdat.isPresent()) {
			EvesSzabadsagAdat evesSzabadsagAdat = optionalEvesSzabadsagAdat.get();
			return new EvesSzabadsagAdatResponseDTO(evesSzabadsagAdat.getId(), evesSzabadsagAdat.getSzemelyitorzs().getTsz(), evesSzabadsagAdat.getEv(), evesSzabadsagAdat.getAlapszabadsag(), evesSzabadsagAdat.getGyermekekUtan(), evesSzabadsagAdat.getFiatalkoru(), evesSzabadsagAdat.getVakSzemely(), evesSzabadsagAdat.getEgyebMunkakor(), evesSzabadsagAdat.getKtKaPotszabadsag(), evesSzabadsagAdat.getKtKaVezetoi(), evesSzabadsagAdat.getEgyebCsokkento(), evesSzabadsagAdat.getTanulmanyi(), evesSzabadsagAdat.getMultEviSzabadsag(), evesSzabadsagAdat.getBszJarandosagAdottEvi(), consumptionTable);
		} else {
			return new EvesSzabadsagAdatResponseDTO(null, dto.getTsz(), dto.getEv(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, consumptionTable);
		}
	}

	private static ConsumptionTableDTO createConsumptionTableDTO(int ev, List<Szabadsag> adottEviSzabadsagok, KivetelnapokHelper kivetelnapok) {
		List<ConsumptionPerTypePerMonthDTO> rows = Lists.newArrayList();
		rows.add(ConsumptionPerTypePerMonthDTO.createEmpty(SZABADSAG));
		rows.add(ConsumptionPerTypePerMonthDTO.createEmpty(BETEGSZABADSAG));
		rows.add(ConsumptionPerTypePerMonthDTO.createEmpty(TEMETESI_SZABADSAG));
		rows.add(ConsumptionPerTypePerMonthDTO.createEmpty(APA_SZABADSAG));
		rows.add(ConsumptionPerTypePerMonthDTO.createEmpty(TANULMANYI_SZABADSAG));
		
		ConsumptionTableDTO consumptionTableDTO = new ConsumptionTableDTO(rows);
		
		FelhasznaltSzabadnapReszletekbolMunkanapokSzamatMeghatarozo munkanapokSzamaMeghatarozo = new FelhasznaltSzabadnapReszletekbolMunkanapokSzamatMeghatarozo(kivetelnapok);
		for (Month honap : MonthIterator.fromYear(ev)) {
			AdottHaviEsAdottJelleguSzabadsagokDTO adottHaviSzabadsagSzabadsagok = new AdottHaviEsAdottJelleguSzabadsagokDTO(new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok).konvertal(adottEviSzabadsagok.stream().filter(new AdottJelleguEsAdottHaviSzabadsagokPredicate(honap, SZABADSAG)).collect(Collectors.toList())));
			AdottHaviEsAdottJelleguSzabadsagokDTO adottHaviBetegszabadsagSzabadsagok = new AdottHaviEsAdottJelleguSzabadsagokDTO(new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok).konvertal(adottEviSzabadsagok.stream().filter(new AdottJelleguEsAdottHaviSzabadsagokPredicate(honap, BETEGSZABADSAG)).collect(Collectors.toList())));
			AdottHaviEsAdottJelleguSzabadsagokDTO adottHaviTemetesiSzabadsagSzabadsagok = new AdottHaviEsAdottJelleguSzabadsagokDTO(new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok).konvertal(adottEviSzabadsagok.stream().filter(new AdottJelleguEsAdottHaviSzabadsagokPredicate(honap, TEMETESI_SZABADSAG)).collect(Collectors.toList())));
			AdottHaviEsAdottJelleguSzabadsagokDTO adottHaviApaSzabadsagSzabadsagok = new AdottHaviEsAdottJelleguSzabadsagokDTO(new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok).konvertal(adottEviSzabadsagok.stream().filter(new AdottJelleguEsAdottHaviSzabadsagokPredicate(honap, APA_SZABADSAG)).collect(Collectors.toList())));
			AdottHaviEsAdottJelleguSzabadsagokDTO adottHaviTanulmanyiSzabadsagSzabadsagok = new AdottHaviEsAdottJelleguSzabadsagokDTO(new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok).konvertal(adottEviSzabadsagok.stream().filter(new AdottJelleguEsAdottHaviSzabadsagokPredicate(honap, TANULMANYI_SZABADSAG)).collect(Collectors.toList())));
			
			int adottHonapbanSzabadnapraElszamoltMunkanapokSzama = munkanapokSzamaMeghatarozo.getMunkanapokSzama(adottHaviSzabadsagSzabadsagok);
			int adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama = munkanapokSzamaMeghatarozo.getMunkanapokSzama(adottHaviBetegszabadsagSzabadsagok);
			int adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama = munkanapokSzamaMeghatarozo.getMunkanapokSzama(adottHaviTemetesiSzabadsagSzabadsagok);
			int adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama = munkanapokSzamaMeghatarozo.getMunkanapokSzama(adottHaviApaSzabadsagSzabadsagok);
			int adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama = munkanapokSzamaMeghatarozo.getMunkanapokSzama(adottHaviTanulmanyiSzabadsagSzabadsagok);
			if (honap.isJanuary()) {
				rows.get(SZABADSAG_ORDER).january = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).januaryDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).january = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).januaryDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).january = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).januaryDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).january = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).januaryDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).january = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).januaryDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isFebruary()) {
				rows.get(SZABADSAG_ORDER).february = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).februaryDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).february = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).februaryDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).february = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).februaryDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).february = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).februaryDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).february = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).februaryDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isMarch()) {
				rows.get(SZABADSAG_ORDER).march = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).marchDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).march = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).marchDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).march = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).marchDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).march = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).marchDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).march = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).marchDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isApril()) {
				rows.get(SZABADSAG_ORDER).april = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).aprilDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).april = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).aprilDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).april = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).aprilDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).april = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).aprilDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).april = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).aprilDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isMay()) {
				rows.get(SZABADSAG_ORDER).may = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).mayDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).may = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).mayDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).may = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).mayDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).may = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).mayDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).may = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).mayDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isJune()) {
				rows.get(SZABADSAG_ORDER).june = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).juneDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).june = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).juneDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).june = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).juneDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).june = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).juneDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).june = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).juneDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isJuly()) {
				rows.get(SZABADSAG_ORDER).july = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).julyDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).july = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).julyDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).july = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).julyDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).july = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).julyDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).july = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).julyDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isAugust()) {
				rows.get(SZABADSAG_ORDER).august = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).augustDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).august = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).augustDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).august = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).augustDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).august = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).augustDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).august = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).augustDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isSeptember()) {
				rows.get(SZABADSAG_ORDER).september = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).septemberDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).september = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).septemberDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).september = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).septemberDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).september = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).septemberDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).september = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).septemberDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isOctober()) {
				rows.get(SZABADSAG_ORDER).october = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).octoberDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).october = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).octoberDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).october = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).octoberDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).october = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).octoberDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).october = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).octoberDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isNovember()) {
				rows.get(SZABADSAG_ORDER).november = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).novemberDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).november = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).novemberDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).november = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).novemberDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).november = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).novemberDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).november = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).novemberDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			} else if (honap.isDecember()) {
				rows.get(SZABADSAG_ORDER).december = adottHonapbanSzabadnapraElszamoltMunkanapokSzama;
				rows.get(SZABADSAG_ORDER).decemberDetails = adottHaviSzabadsagSzabadsagok;
				rows.get(BETEGSZABADSAG_ORDER).december = adottHonapbanBetegszabadsagraElszamoltMunkanapokSzama;
				rows.get(BETEGSZABADSAG_ORDER).decemberDetails = adottHaviBetegszabadsagSzabadsagok;
				rows.get(TEMETESI_SZABADSAG_ORDER).december = adottHonapbanTemetesoSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TEMETESI_SZABADSAG_ORDER).decemberDetails = adottHaviTemetesiSzabadsagSzabadsagok;
				rows.get(APA_SZABADSAG_ORDER).december = adottHonapbanApaSzabadsagraElszamoltMunkanapokSzama;
				rows.get(APA_SZABADSAG_ORDER).decemberDetails = adottHaviApaSzabadsagSzabadsagok;
				rows.get(TANULMANYI_SZABADSAG_ORDER).december = adottHonapbanTanulmanyiSzabadsagraElszamoltMunkanapokSzama;
				rows.get(TANULMANYI_SZABADSAG_ORDER).decemberDetails = adottHaviTanulmanyiSzabadsagSzabadsagok;
			}
		}
		return consumptionTableDTO;
	}
	
	private static class FelhasznaltSzabadnapReszletekbolMunkanapokSzamatMeghatarozo {

		private KivetelnapokHelper kivetelnapok;
		
		FelhasznaltSzabadnapReszletekbolMunkanapokSzamatMeghatarozo(KivetelnapokHelper kivetelnapok) {
			this.kivetelnapok = kivetelnapok;
		}

		public int getMunkanapokSzama(AdottHaviEsAdottJelleguSzabadsagokDTO adottHaviEsAdottJelleguSzabadsagokDTO) {
			int munkanapokSzama = 0;
			for (SzabadsagFelhasznalasResponseDTO reszlet : adottHaviEsAdottJelleguSzabadsagokDTO.reszletek) {
				for (Date date : new DateIterator(reszlet.kezdet, reszlet.veg)) {
					if (kivetelnapok.isMunkanap(date)) {
						munkanapokSzama++;
					}
				}
			}
			return munkanapokSzama;
		}
	}
}
