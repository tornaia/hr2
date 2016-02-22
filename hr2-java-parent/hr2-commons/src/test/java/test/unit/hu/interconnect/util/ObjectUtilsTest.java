package test.unit.hu.interconnect.util;

import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import test.unit.AbstractUnitTest;

public class ObjectUtilsTest extends AbstractUnitTest {

	@Test
	public void equalsAndNotNullWhenBothParamsAreNull() {
		assertFalse(equalsAndNotNull(null, null));
	}
	
	@Test
	public void equalsAndNotNullWhenParam1IsNull() {
		assertFalse(equalsAndNotNull(null, new Object()));
	}
	
	@Test
	public void equalsAndNotNullWhenParam2IsNull() {
		assertFalse(equalsAndNotNull(new Object(), null));
	}
	
	@Test
	public void equalsAndNotNullWhenObjectsAreIdentical() {
		Object object = new Object();
		assertTrue(equalsAndNotNull(object, object));
	}
	
	@Test
	public void equalsAndNotNullWhenObjectsAreEquals() {
		assertTrue(equalsAndNotNull(new Integer(123123), new Integer(123123)));
	}
	
	@Test
	public void equalsAndNotNullWhenObjectsAreEquals2() {
		assertTrue(equalsAndNotNull(5L, 5L));
	}
}
