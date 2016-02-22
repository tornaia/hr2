package test.unit.hu.interconnect.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hu.interconnect.exception.UzletiHiba;
import test.unit.AbstractUnitTest;

public class UzletiHibaTest extends AbstractUnitTest {

	@Test
	public void uzletiHibaRootCauseNelkul() {
		assertEquals("message", new UzletiHiba("message").getMessage());
	}
	
	@Test
	public void uzletiHibaRootCausezal() {
		NullPointerException rootCause = new NullPointerException("NPE");
		UzletiHiba uzletiHiba = new UzletiHiba("message", rootCause);
		
		assertEquals("message", uzletiHiba.getMessage());
		assertEquals(rootCause, uzletiHiba.getCause());
	}
}
