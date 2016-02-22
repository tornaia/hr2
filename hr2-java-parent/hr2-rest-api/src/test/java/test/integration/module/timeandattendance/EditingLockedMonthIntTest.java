package test.integration.module.timeandattendance;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.access.AccessDeniedException;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;
import hu.interconnect.util.DateUtils;
import test.builder.FelhasznaloCreateDTOBuilder;
import test.builder.HaviJelenletiIvEditDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.HaviJelenletiIvResponseDTOMatcher;

public class EditingLockedMonthIntTest extends AbstractRestAPIIntTest {

	private Date actualMonth = DateUtils.aktualisIdo();
	
	private int egyTetszolegesTsz;
	
	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();
		this.egyTetszolegesTsz = personaldataRestController.findall().get(0);
	}
	
	@Test
	public void adminLocksMonth() {
		HaviJelenletiIvResponseDTO megSzabadonSzerkeszthetoJelenletiIv = timeandattendanceRestController.getHaviJelenletiIv(egyTetszolegesTsz, actualMonth);
		assertThat(megSzabadonSzerkeszthetoJelenletiIv, new HaviJelenletiIvResponseDTOMatcher().honap(actualMonth).tsz(egyTetszolegesTsz).honapSzerkesztheto(true));
		
		timeandattendanceRestController.lockMonth(actualMonth);

		HaviJelenletiIvResponseDTO marNemSzerkeszthetoJelenletiIv = timeandattendanceRestController.getHaviJelenletiIv(egyTetszolegesTsz, actualMonth);
		assertThat(marNemSzerkeszthetoJelenletiIv, new HaviJelenletiIvResponseDTOMatcher().honap(actualMonth).tsz(egyTetszolegesTsz).honapSzerkesztheto(false));
	}
	
	@Test
	public void adminUnlocksMonth() {
		timeandattendanceRestController.lockMonth(actualMonth);

		HaviJelenletiIvResponseDTO nemSzerkeszthetoJelenletiIv = timeandattendanceRestController.getHaviJelenletiIv(egyTetszolegesTsz, actualMonth);
		assertThat(nemSzerkeszthetoJelenletiIv, new HaviJelenletiIvResponseDTOMatcher().honap(actualMonth).tsz(egyTetszolegesTsz).honapSzerkesztheto(false));
		
		timeandattendanceRestController.unlockMonth(actualMonth);
		
		HaviJelenletiIvResponseDTO ismetSzerkeszthetoJelenletiIv = timeandattendanceRestController.getHaviJelenletiIv(egyTetszolegesTsz, actualMonth);
		assertThat(ismetSzerkeszthetoJelenletiIv, new HaviJelenletiIvResponseDTOMatcher().honap(actualMonth).tsz(egyTetszolegesTsz).honapSzerkesztheto(true));
	}
	
	@Test
	public void adminLocksUnlocksLocksMonth() {
		timeandattendanceRestController.lockMonth(actualMonth);
		timeandattendanceRestController.unlockMonth(actualMonth);
		timeandattendanceRestController.lockMonth(actualMonth);
		
		HaviJelenletiIvResponseDTO marNemSzerkeszthetoJelenletiIv = timeandattendanceRestController.getHaviJelenletiIv(egyTetszolegesTsz, actualMonth);
		assertThat(marNemSzerkeszthetoJelenletiIv, new HaviJelenletiIvResponseDTOMatcher().honap(actualMonth).tsz(egyTetszolegesTsz).honapSzerkesztheto(false));
	}
	
	@Test
	public void workerCanEditNotLockedMonth() {
		usersRestController.create(new FelhasznaloCreateDTOBuilder().nev("dolgozo").jelszo("dolgozo").locale(Locale.hu_HU).szerep(Szerep.DOLGOZO).tsz(egyTetszolegesTsz).enabled(true).letrehoz());
		
		felhasznaloBejelentkezik("dolgozo", "dolgozo");
		timeandattendanceRestController.edit(new HaviJelenletiIvEditDTOBuilder().tsz(egyTetszolegesTsz).honap(actualMonth).jelenletiAdatok(emptyList()).letrehoz());
		
		HaviJelenletiIvResponseDTO nemSzerkeszthetoJelenletiIv = timeandattendanceRestController.getHaviJelenletiIv(egyTetszolegesTsz, actualMonth);
		assertThat(nemSzerkeszthetoJelenletiIv, new HaviJelenletiIvResponseDTOMatcher().honap(actualMonth).tsz(egyTetszolegesTsz).honapSzerkesztheto(true));
	}
	
	@Test
	public void workerCanEditUnlockedMonth() {
		usersRestController.create(new FelhasznaloCreateDTOBuilder().nev("dolgozo").jelszo("dolgozo").locale(Locale.hu_HU).szerep(Szerep.DOLGOZO).tsz(egyTetszolegesTsz).enabled(true).letrehoz());

		timeandattendanceRestController.lockMonth(actualMonth);
		timeandattendanceRestController.unlockMonth(actualMonth);
		
		felhasznaloBejelentkezik("dolgozo", "dolgozo");
		timeandattendanceRestController.edit(new HaviJelenletiIvEditDTOBuilder().tsz(egyTetszolegesTsz).honap(actualMonth).jelenletiAdatok(Collections.emptyList()).letrehoz());
	}
	
	@Test
	public void workerCantEditLockedMonth() {
		expectedException.expect(ProgramozasiHiba.class);
		expectedException.expectMessage("Adott honap le van zarva megis szerkeszteni probalta valaki!");
		
		usersRestController.create(new FelhasznaloCreateDTOBuilder().nev("dolgozo").jelszo("dolgozo").locale(Locale.hu_HU).szerep(Szerep.DOLGOZO).tsz(egyTetszolegesTsz).enabled(true).letrehoz());
		
		timeandattendanceRestController.lockMonth(actualMonth);

		HaviJelenletiIvResponseDTO nemSzerkeszthetoJelenletiIv = timeandattendanceRestController.getHaviJelenletiIv(egyTetszolegesTsz, actualMonth);
		assertThat(nemSzerkeszthetoJelenletiIv, new HaviJelenletiIvResponseDTOMatcher().honap(actualMonth).tsz(egyTetszolegesTsz).honapSzerkesztheto(false));
		
		felhasznaloBejelentkezik("dolgozo", "dolgozo");
		
		timeandattendanceRestController.edit(new HaviJelenletiIvEditDTOBuilder().tsz(egyTetszolegesTsz).honap(actualMonth).letrehoz());
	}
	
	@Test
	public void betekintoCantEditMonth() {
		expectedException.expect(AccessDeniedException.class);
		expectedException.expectMessage("Access is denied");
		
		usersRestController.create(new FelhasznaloCreateDTOBuilder().nev("betekinto").jelszo("betekinto").locale(Locale.hu_HU).szerep(Szerep.BETEKINTO).enabled(true).letrehoz());

		felhasznaloBejelentkezik("betekinto", "betekinto");
		
		timeandattendanceRestController.edit(new HaviJelenletiIvEditDTOBuilder().tsz(egyTetszolegesTsz).honap(actualMonth).letrehoz());
	}
	
	@Test
	public void adminCanEditLockedMonth() {
		timeandattendanceRestController.lockMonth(actualMonth);
		timeandattendanceRestController.edit(new HaviJelenletiIvEditDTOBuilder().tsz(egyTetszolegesTsz).honap(actualMonth).jelenletiAdatok(newArrayList()).letrehoz());
	}
}
