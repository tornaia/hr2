package test.unit.hu.interconnect.hr.module.idozitettfuttatas.orvosivizsgalatlejarat;

import hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat.KuldendoSMSAllomanyok;
import test.unit.AbstractBackendUnitTest;

import org.junit.Test;

public class KuldendoSMSAllomanyokTest extends AbstractBackendUnitTest {

	@Test(expected=UnsupportedOperationException.class)
	public void onlyAddOperationIsSupported() {
		KuldendoSMSAllomanyok kuldendoSMSAllomanyok = new KuldendoSMSAllomanyok();
		kuldendoSMSAllomanyok.add(null);
		kuldendoSMSAllomanyok.iterator().remove();
	}
}
