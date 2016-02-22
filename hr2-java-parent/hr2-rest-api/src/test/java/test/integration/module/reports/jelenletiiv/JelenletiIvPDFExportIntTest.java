package test.integration.module.reports.jelenletiiv;

import static hu.interconnect.util.FileUtils.getClasspathByteArray;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;

public class JelenletiIvPDFExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void szemelyitorzsetFelvesz() {
		adminBejelentkezik();
		
		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
											.tsz(2)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder() 
																		.vezeteknev("Papp")
																		.keresztnev("Krisztina")
																		.email("papp.krisztina@szolgaltato.xy")
																		.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																		.allomanymod(Allomanymod.AKTIV)
																		.letrehoz())
											.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
	}
	
	@Test
	public void exportalaskorAVartPDFGeneralodik() {
		byte[] actualPDFBytes = reportsView.getJelenletiIv("2013.01");
		byte[] expectedPDFBytes = getClasspathByteArray("/riportok/elvart_jelenleti_iv.pdf");
		int randomness_toleration = 500;
		assertEquals(expectedPDFBytes.length, actualPDFBytes.length, randomness_toleration);
	}
}
