package test.unit.hu.interconnect.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hu.interconnect.exception.UzletiFigyelmeztetes;
import test.unit.AbstractUnitTest;

public class UzletiFigyelmeztetesTest extends AbstractUnitTest {

	@Test
	public void uzletiHibaRootCauseNelkul() {
		assertEquals("message", new UzletiFigyelmeztetes("message").getMessage());
	}
	
	@Test
	public void uzletiHibaRootCausezal() {
		NullPointerException rootCause = new NullPointerException("NPE");
		UzletiFigyelmeztetes uzletiFigyelmeztetes = new UzletiFigyelmeztetes("message", rootCause);
		
		assertEquals("message", uzletiFigyelmeztetes.getMessage());
		assertEquals(rootCause, uzletiFigyelmeztetes.getCause());
	}
}
