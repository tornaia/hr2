package test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import hu.interconnect.util.DateUtils;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractUnitTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Before
	@After
	public final void tesztDatumotElvet() {
		DateUtils.alaphelyzet();
	}
}
