package hu.interconnect.hr.module.valuesets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.interconnect.exception.UzletiHiba;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Allampolgarsag;
import hu.interconnect.hr.domain.ErtekkeszletElem;
import hu.interconnect.hr.domain.FEOR;
import hu.interconnect.hr.domain.FoglalkozasiViszony;
import hu.interconnect.hr.domain.FoglalkoztatasJellege;
import hu.interconnect.hr.domain.Koltseghely;
import hu.interconnect.hr.domain.Munkakor;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.domain.SzervezetiEgyseg;

@Component
public class ValuesetDeleteValidator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;

	public <T extends ErtekkeszletElem> void validal(T elem) {
		ErtekkeszletElemTipus tipus = elem.getTipus();
		if (tipus == ErtekkeszletElemTipus.ALLAMPOLGARSAG) {
			List<Szemelyitorzs> adottAllampolgarsaguSzemelyitorzsek = szemelyitorzsDAO.findByAllampolgarsag((Allampolgarsag) elem);
			if (!adottAllampolgarsaguSzemelyitorzsek.isEmpty()) {
				throw new UzletiHiba("UZLETIHIBA_ALLAMPOLGARSAG_HASZNALATBAN_VAN");
			}
		} else if (tipus == ErtekkeszletElemTipus.FEOR) {
			List<Szemelyitorzs> adottFEORuSzemelyitorzsek = szemelyitorzsDAO.findByFEOR((FEOR) elem);
			if (!adottFEORuSzemelyitorzsek.isEmpty()) {
				throw new UzletiHiba("UZLETIHIBA_FEOR_HASZNALATBAN_VAN");
			}
		} else if (tipus == ErtekkeszletElemTipus.FOGLALKOZASI_VISZONY) {
			List<Szemelyitorzs> adottFoglalkozasiViszonyuSzemelyitorzsek = szemelyitorzsDAO.findByFoglalkozasiViszony((FoglalkozasiViszony) elem);
			if (!adottFoglalkozasiViszonyuSzemelyitorzsek.isEmpty()) {
				throw new UzletiHiba("UZLETIHIBA_FOGLALKOZASI_VISZONY_HASZNALATBAN_VAN");
			}
		} else if (tipus == ErtekkeszletElemTipus.FOGLALKOZTATAS_JELLEGE) {
			List<Szemelyitorzs> adottFoglalkoztatasJelleguSzemelyitorzsek = szemelyitorzsDAO.findByFoglalkoztatasJellege((FoglalkoztatasJellege) elem);
			if (!adottFoglalkoztatasJelleguSzemelyitorzsek.isEmpty()) {
				throw new UzletiHiba("UZLETIHIBA_FOGLALKOZTATAS_JELLEGE_HASZNALATBAN_VAN");
			}
		} else if (tipus == ErtekkeszletElemTipus.KOLTSEGHELY) {
			List<Szemelyitorzs> adottKoltseghelyuSzemelyitorzsek = szemelyitorzsDAO.findByKoltseghely((Koltseghely) elem);
			if (!adottKoltseghelyuSzemelyitorzsek.isEmpty()) {
				throw new UzletiHiba("UZLETIHIBA_KOLTSEGHELY_HASZNALATBAN_VAN");
			}
		} else if (tipus == ErtekkeszletElemTipus.MUNKAKOR) {
			List<Szemelyitorzs> adottMunkakoruSzemelyitorzsek = szemelyitorzsDAO.findByMunkakor((Munkakor) elem);
			if (!adottMunkakoruSzemelyitorzsek.isEmpty()) {
				throw new UzletiHiba("UZLETIHIBA_MUNKAKOR_HASZNALATBAN_VAN");
			}
		} else if (tipus == ErtekkeszletElemTipus.SZERVEZETI_EGYSEG) {
			List<Szemelyitorzs> adottSzervezetiEgyseguSzemelyitorzsek = szemelyitorzsDAO.findBySzervezetiEgyseg((SzervezetiEgyseg) elem);
			if (!adottSzervezetiEgyseguSzemelyitorzsek.isEmpty()) {
				throw new UzletiHiba("UZLETIHIBA_SZERVEZETI_EGYSEG_HASZNALATBAN_VAN");
			}
		}
	}
}
