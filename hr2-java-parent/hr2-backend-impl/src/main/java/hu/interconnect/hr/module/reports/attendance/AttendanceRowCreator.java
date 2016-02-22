package hu.interconnect.hr.module.reports.attendance;

import static hu.interconnect.util.DateUtils.getHoElsoNapja;
import static hu.interconnect.util.DateUtils.getHoUtolsoNapja;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.dao.FelhasznaloDAO;
import hu.interconnect.hr.dao.KivetelnapDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.domain.SzervezetiEgyseg;
import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;
import hu.interconnect.hr.security.AuthorityConstants;
import hu.interconnect.util.DateIterator;

@Component
public class AttendanceRowCreator {

	@Autowired
	private FelhasznaloDAO felhasznaloDAO;
	
	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private KivetelnapDAO kivetelnapDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(Date honap) {
		List<Felhasznalo> aktivFelhasznalok = felhasznaloDAO.findOsszesAktiv();
		List<Szemelyitorzs> szemelyitorzsek = szemelyitorzsDAO.findOsszesFetchelve();
		
		return szemelyitorzsek.stream()
				.filter(new SzemelyitorzsAktivEsNemTartozikHozzaAktivFelhasznalo(aktivFelhasznalok))
				.map(new AttendanceRowDataCreator(honap))
				.collect(Collectors.toList());
	}
	
	private final class AttendanceRowDataCreator implements Function<Szemelyitorzs, Map<String, Object>> {
		
		private Date honap;
		
		private AttendanceRowDataCreator(Date honap) {
			this.honap = honap;
		}

		@Override
		public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
			NullValueToEmptyStringMap lap = new NullValueToEmptyStringMap();
			SzervezetiEgyseg szervezetiEgyseg = szemelyitorzs.getMunkakoriBesorolas().getSzervezetiEgyseg();
			lap.putErtekkeszletElem("szervezetiEgyseg", szervezetiEgyseg);
			lap.put("tsz", szemelyitorzs.getTsz());
			lap.put("teljesNev", szemelyitorzs.getTeljesNev());
			lap.putDateWithYearMonth("honap", honap);
			
			StringBuilder tablazatTorzsHtml = new StringBuilder();
			KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(kivetelnapDAO.findAll());
			NapotKitoltendoHtmlSorraAtalakito jelenletiAdatotHtmlSorraAtalakito = new NapotKitoltendoHtmlSorraAtalakito(kivetelnapok);
			for (Date date : new DateIterator(getHoElsoNapja(honap), getHoUtolsoNapja(honap))) {
				String kitoltottHtmlSor = jelenletiAdatotHtmlSorraAtalakito.apply(date);
				tablazatTorzsHtml.append(kitoltottHtmlSor);
			}
			
			lap.put("tablazatTorzsHtml", tablazatTorzsHtml.toString());
			
			return lap.getMap();
		}
	}
}
