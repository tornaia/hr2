package test.unit.hu.interconnect.pdf;

import static hu.interconnect.util.FileUtils.getClasspathByteArray;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.junit.Test;

import hu.interconnect.pdf.PDFOsszefuzo;
import test.unit.AbstractBackendUnitTest;

public class PDFOsszefuzoTest extends AbstractBackendUnitTest {

	@Test
	public void uresListatOsszefuz() {
		byte[] actualPDFBytes = PDFOsszefuzo.appendAll(new ArrayList<ByteArrayOutputStream>());
		byte[] expectedPDFBytes = getClasspathByteArray("/pdf/elvart_ures.pdf");
		// minden generalaskor egy kicsit mas valami benne... created, modified
		assertEquals(expectedPDFBytes.length, actualPDFBytes.length);
	}
}
