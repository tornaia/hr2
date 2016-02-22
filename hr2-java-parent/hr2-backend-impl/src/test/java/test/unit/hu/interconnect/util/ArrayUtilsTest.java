package test.unit.hu.interconnect.util;

import static hu.interconnect.util.ArrayUtils.removeNullOrEmptyElements;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import test.unit.AbstractBackendUnitTest;

public class ArrayUtilsTest extends AbstractBackendUnitTest {

	@Test
	public void uresTomb() {
		assertThat(removeNullOrEmptyElements(new String[0]), arrayWithSize(0));
	}
	
	@Test
	public void nullosTomb() {
		assertThat(removeNullOrEmptyElements(new String[]{null}), arrayWithSize(0));
	}
	
	@Test
	public void egyUresElemuTomb() {
		assertThat(removeNullOrEmptyElements(new String[]{""}), arrayWithSize(0));
	}
	
	@Test
	public void egyElemuTomb() {
		assertThat(removeNullOrEmptyElements(new String[]{"a"}), arrayContaining("a"));
	}
	
	@Test
	public void sorrendetMegtartja() {
		assertThat(removeNullOrEmptyElements(new String[]{"a", "b"}), arrayContaining("a", "b"));
	}
	
	@Test
	public void vegyes() {
		assertThat(removeNullOrEmptyElements(new String[]{"a", null, "", "b"}), arrayContaining("a", "b"));
	}
}
