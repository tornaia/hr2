package test.unit.hu.interconnect.util;

import static hu.interconnect.util.StringUtils.capitalizeFully;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import test.unit.AbstractBackendUnitTest;

public class StringUtilsTest extends AbstractBackendUnitTest {

	@Test
	public void capitalizeFullyNullTest() {
		assertNull(capitalizeFully(null));
	}
	
	@Test
	public void capitalizeFullyEmptyTest() {
		assertEquals("", capitalizeFully(""));
	}
	
	@Test
	public void capitalizeFullyNumberTest() {
		assertEquals("1", capitalizeFully("1"));
	}
	
	@Test
	public void capitalizeFullyNumberThanCharTest() {
		assertEquals("1a", capitalizeFully("1a"));
	}
	
	@Test
	public void capitalizeFullyTest() {
		assertEquals("A B C D", capitalizeFully("a b c d"));
	}
	
	@Test
	public void capitalizeFullyBySpaceTest() {
		assertEquals("Abc Def", capitalizeFully("abC dEf"));
	}
	
	@Test
	public void capitalizeFullyByMinusSignTest() {
		assertEquals("Abc-Def", capitalizeFully("abC-dEf"));
	}
	
}
