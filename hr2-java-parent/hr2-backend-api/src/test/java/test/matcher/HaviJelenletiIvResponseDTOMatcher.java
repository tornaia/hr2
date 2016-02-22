package test.matcher;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvResponseDTO;
import hu.interconnect.hr.backend.api.dto.JelenletiAdatDTO;

public class HaviJelenletiIvResponseDTOMatcher extends AbstractTypeSafeDiagnosingMatcher<HaviJelenletiIvResponseDTO> {

	private Matcher<Date> honap = new IsAnything<>();

	private Matcher<Integer> tsz = new IsAnything<>();
	
	private Matcher<Iterable<? extends JelenletiAdatDTO>> jelenletiAdatok = new IsAnything<>();

	private Matcher<Boolean> honapSzerkesztheto = new IsAnything<>();

	public HaviJelenletiIvResponseDTOMatcher honap(Date honap) {
		this.honap = CoreMatchers.is(honap);
		return this;
	}
	
	public HaviJelenletiIvResponseDTOMatcher tsz(Integer tsz) {
		this.tsz = CoreMatchers.is(tsz);
		return this;
	}
	
	public HaviJelenletiIvResponseDTOMatcher jelenletiAdatok(Matcher<Iterable<? extends JelenletiAdatDTO>> jelenletiAdatok) {
		this.jelenletiAdatok = CoreMatchers.is(jelenletiAdatok);
		return this;
	}
	
	public HaviJelenletiIvResponseDTOMatcher honapSzerkesztheto(boolean honapSzerkesztheto) {
		this.honapSzerkesztheto = CoreMatchers.is(honapSzerkesztheto);
		return this;
	}
	
	@Override
	protected boolean matchesSafely(HaviJelenletiIvResponseDTO item, Description mismatchDescription) {
		return matches(honap, item.honap, "honap value: ", mismatchDescription) &&
				matches(tsz, item.tsz, "tsz value: ", mismatchDescription) &&
				matches(jelenletiAdatok, item.jelenletiAdatok, "jelenletiAdatok value: ", mismatchDescription) &&
				matches(honapSzerkesztheto, item.honapSzerkesztheto, "honapSzerkesztheto value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(HaviJelenletiIvResponseDTO.class.getSimpleName())
        	.appendText(", honap: ").appendDescriptionOf(honap)
        	.appendText(", tsz: ").appendDescriptionOf(tsz)
        	.appendText(", jelenletiAdatok: ").appendDescriptionOf(jelenletiAdatok)
        	.appendText(", honapSzerkesztheto: ").appendDescriptionOf(honapSzerkesztheto);
	}
}
