package test.matcher;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;
import test.matcher.AbstractTypeSafeDiagnosingMatcher;

public class SzabadsagFelhasznalasResponseDTOMatcher extends AbstractTypeSafeDiagnosingMatcher<SzabadsagFelhasznalasResponseDTO> {

	private Matcher<Integer> tsz = new IsAnything<>();
	
	private Matcher<Date> kezdet = new IsAnything<>();
	
	private Matcher<Date> veg = new IsAnything<>();
	
	private Matcher<FelhasznaltSzabadnapJelleg> jelleg = new IsAnything<>();
	
	private Matcher<Integer> munkanapokSzama = new IsAnything<>();
	
	public SzabadsagFelhasznalasResponseDTOMatcher tsz(Integer tsz) {
		this.tsz = CoreMatchers.is(tsz);
		return this;
	}
	
	public SzabadsagFelhasznalasResponseDTOMatcher kezdet(Matcher<Date> kezdet) {
		this.kezdet = kezdet;
		return this;
	}
	
	public SzabadsagFelhasznalasResponseDTOMatcher kezdet(String kezdetStr) {
		this.kezdet = CoreMatchers.is(parseNap(kezdetStr));
		return this;
	}
	
	public SzabadsagFelhasznalasResponseDTOMatcher veg(Matcher<Date> veg) {
		this.veg = veg;
		return this;
	}
	
	public SzabadsagFelhasznalasResponseDTOMatcher veg(String vegStr) {
		this.veg = CoreMatchers.is(parseNap(vegStr));
		return this;
	}
	
	public SzabadsagFelhasznalasResponseDTOMatcher jelleg(FelhasznaltSzabadnapJelleg jelleg) {
		this.jelleg = CoreMatchers.is(jelleg);
		return this;
	}
	
	public SzabadsagFelhasznalasResponseDTOMatcher munkanapokSzama(Integer munkanapokSzama) {
		this.munkanapokSzama = CoreMatchers.is(munkanapokSzama);
		return this;
	}
	
	@Override
	protected boolean matchesSafely(SzabadsagFelhasznalasResponseDTO item, Description mismatchDescription) {
		return matches(tsz, item.tsz, "tsz value: ", mismatchDescription) &&
				matches(kezdet, item.kezdet, "kezdet value: ", mismatchDescription) &&
				matches(veg, item.veg, "veg value: ", mismatchDescription) &&
				matches(jelleg, item.jelleg, "jelleg value: ", mismatchDescription) &&
				matches(munkanapokSzama, item.munkanapokSzama, "munkanapokSzama value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(SzabadsagFelhasznalasResponseDTO.class.getSimpleName())
    	.appendText(", tsz: ").appendDescriptionOf(tsz)
    	.appendText(", kezdet: ").appendDescriptionOf(kezdet)
    	.appendText(", veg: ").appendDescriptionOf(veg)
    	.appendText(", jelleg: ").appendDescriptionOf(jelleg)
    	.appendText(", munkanapokSzama: ").appendDescriptionOf(munkanapokSzama);
	}
}