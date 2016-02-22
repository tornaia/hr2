package test.unit.hu.interconnect.hr.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hu.interconnect.hr.domain.SzemelyiAdatok;
import test.builder.SzemelyiAdatokBuilder;
import test.unit.AbstractBackendUnitTest;

public class SzemelyiAdatokTest extends AbstractBackendUnitTest {

	@Test
	public void getTeljesnevHaVanVezeteknevEsKeresztnev() {
		SzemelyiAdatok sza = new SzemelyiAdatokBuilder()
									.vezeteknev("B")
									.keresztnev("C")
									.letrehoz();
		
		assertEquals("B C", sza.getTeljesNev());
	}
	
	@Test
	public void getTeljesnevHaUresVezeteknev() {
		SzemelyiAdatok sza = new SzemelyiAdatokBuilder()
									.vezeteknev("")
									.keresztnev("C")
									.letrehoz();
		
		assertEquals("C", sza.getTeljesNev());
	}
	
	@Test
	public void getTeljesnevHaSpaceAVezeteknev() {
		SzemelyiAdatok sza = new SzemelyiAdatokBuilder()
									.vezeteknev(" ")
									.keresztnev("C")
									.letrehoz();
		
		assertEquals("  C", sza.getTeljesNev());
	}
	
	@Test
	public void getTeljesNevHaMindenTagEgySpace() {
		SzemelyiAdatok sza = new SzemelyiAdatokBuilder()
									.vezeteknev(" ")
									.keresztnev(" ")
									.letrehoz();
		
		assertEquals("   ", sza.getTeljesNev());
	}
	
	@Test
	public void getTeljesNevHaNincsKeresztnev() {
		SzemelyiAdatok sza = new SzemelyiAdatokBuilder()
									.vezeteknev("B")
									.keresztnev(null)
									.letrehoz();
		
		assertEquals("B", sza.getTeljesNev());
	}
}
