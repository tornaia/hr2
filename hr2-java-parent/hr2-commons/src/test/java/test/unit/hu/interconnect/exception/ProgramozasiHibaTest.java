package test.unit.hu.interconnect.exception;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hu.interconnect.exception.ProgramozasiHiba;
import test.unit.AbstractUnitTest;

public class ProgramozasiHibaTest extends AbstractUnitTest {

	@Test
	public void runtimeException() {
		assertTrue(RuntimeException.class.isAssignableFrom(ProgramozasiHiba.class));
	}
}
