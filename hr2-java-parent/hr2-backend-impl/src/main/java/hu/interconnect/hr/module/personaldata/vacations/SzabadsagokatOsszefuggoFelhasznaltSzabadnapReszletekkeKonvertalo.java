package hu.interconnect.hr.module.personaldata.vacations;

import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Iterables.getLast;
import static com.google.common.collect.Iterables.isEmpty;
import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.util.DateUtils.getNapokSzama;
import static hu.interconnect.util.DateUtils.hozzaadNapot;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;
import hu.interconnect.util.DateIterator;

public class SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo {

	private KivetelnapokHelper kivetelnapok;
	
	public SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(KivetelnapokHelper kivetelnapok) {
		this.kivetelnapok = kivetelnapok;
	}
	
	public List<SzabadsagFelhasznalasResponseDTO> konvertal(Iterable<Szabadsag> szabadsagok) {
		if (szabadsagok == null) {
			throw new IllegalArgumentException("A bemeno parameter null!");
		}
		
		if (isEmpty(szabadsagok)) {
			return newArrayList();
		}
		
		List<Szabadsag> rendezettSzabadsagok = Lists.newArrayList(szabadsagok);
		Collections.sort(rendezettSzabadsagok, new SzabadsagDatumComparator());
		
		Date kezdet = rendezettSzabadsagok.get(0).getDatum();
		Date veg = getLast(rendezettSzabadsagok).getDatum(); 
		int napokSzama = getNapokSzama(kezdet, veg);
		// 0 -> ledolgozott munkanap
		// 1 -> pihenonap
		// 2 -> kivett szabadnap
		int[] b = new int[napokSzama];
		
		for (int i=0;i<napokSzama;++i) {
			Date vizsgaltNap = hozzaadNapot(kezdet, i);
			if (kivetelnapok.isPihenonap(vizsgaltNap)) {
				b[i] = 1;
			}
		}
		
		for (Szabadsag szabadsag : rendezettSzabadsagok) {
			int idx = getNapokSzama(kezdet, szabadsag.getDatum()) - 1;
			b[idx] = 2;
		}
		
		List<SzabadsagFelhasznalasResponseDTO> osszefuggoSzabadnapok = newArrayList();

		Szemelyitorzs szemelyitorzs = get(rendezettSzabadsagok, 0).getSzemelyitorzs();
		FelhasznaltSzabadnapJelleg jelleg = get(rendezettSzabadsagok, 0).getJelleg();
		
		DateIterator dayIterator = new DateIterator(kezdet, veg);
		int cursor = 0;
		for (Date day : dayIterator) {
			int elozonapBi = cursor-1 >= 0 ? b[cursor-1] : 0;
			int aktualisnapBi = b[cursor];
			int kovetkezonapBi = b.length > cursor+1 ? b[cursor+1] : 0;
			if (elozonapBi == 0 && aktualisnapBi == 2) {
				osszefuggoSzabadnapok.add(new SzabadsagFelhasznalasResponseDTO(szemelyitorzs.getTsz(), day, day, jelleg, 1));
			} else if (!adottIndexElottVanVanAkarhanyEggyesEsLegalabbEgyKettes(b, cursor) && aktualisnapBi == 2 && elozonapBi != 2) {
				osszefuggoSzabadnapok.add(new SzabadsagFelhasznalasResponseDTO(szemelyitorzs.getTsz(), day, day, jelleg, 1));
			}
			
			if ((aktualisnapBi == 1 || aktualisnapBi == 2) && (kovetkezonapBi == 1 || kovetkezonapBi == 2) && adottIndexUtanVanAkarhanyEggyesEsLegalabbEgyKettes(b, cursor)) {
				SzabadsagFelhasznalasResponseDTO eddigiUtolsoElem = getLast(osszefuggoSzabadnapok);
				if (aktualisnapBi == 1 && adottIndexElottVanVanAkarhanyEggyesEsLegalabbEgyKettes(b, cursor)) {
					egyPihenonappalMeghosszabbit(eddigiUtolsoElem);
				} else if (aktualisnapBi == 2) {
					egyMunkanappalMeghosszabbit(eddigiUtolsoElem);
				}
			}
			cursor++;
		}
		
		return osszefuggoSzabadnapok;
	}
	
	private static void egyMunkanappalMeghosszabbit(SzabadsagFelhasznalasResponseDTO dto) {
		dto.veg = hozzaadNapot(dto.veg, 1);
		dto.munkanapokSzama++;
	}
	
	private static void egyPihenonappalMeghosszabbit(SzabadsagFelhasznalasResponseDTO dto) {
		dto.veg = hozzaadNapot(dto.veg, 1);
	}

	private static boolean adottIndexElottVanVanAkarhanyEggyesEsLegalabbEgyKettes(int[] b, int cursor) {
		for (int i=cursor-1;i>=0;--i) {
			if (b[i] == 2) {
				return true;
			} else if (b[i] == 0) {
				return false;
			}
		}
		return false;
	}

	private static boolean adottIndexUtanVanAkarhanyEggyesEsLegalabbEgyKettes(int[] b, int cursor) {
		for (int i=cursor+1;i<b.length;++i) {
			if (b[i] == 1) {
				continue;
			} else if (b[i] == 2) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
