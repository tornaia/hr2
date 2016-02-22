package test.matcher;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import hu.interconnect.hr.backend.api.enumeration.Szerep;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;
import test.matcher.AbstractTypeSafeDiagnosingMatcher;

public class FelhasznaloMatcher extends AbstractTypeSafeDiagnosingMatcher<Felhasznalo> {

	private Matcher<String> nev = new IsAnything<>();

	private Matcher<String> jelszo = new IsAnything<>();

	private Matcher<Szerep> szerep = new IsAnything<>();

	private Matcher<Szemelyitorzs> szemelyitorzs = new IsAnything<>();

	private Matcher<Boolean> enabled = new IsAnything<>();
	
	public FelhasznaloMatcher adottNevvel(String nev) {
		this.nev = CoreMatchers.is(nev);
		return this;
	}

	public FelhasznaloMatcher adottJelszoval(String jelszo) {
		this.jelszo = CoreMatchers.is(jelszo);
		return this;
	}

	public FelhasznaloMatcher adottSzereppel(Szerep szerep) {
		this.szerep = CoreMatchers.is(szerep);
		return this;
	}

	public FelhasznaloMatcher adottSzemelyitorzzsel(Matcher<Szemelyitorzs> szemelyitorzs) {
		this.szemelyitorzs = szemelyitorzs;
		return this;
	}

	public FelhasznaloMatcher adottEnableddel(boolean enabled) {
		this.enabled = CoreMatchers.is(enabled);
		return this;
	}

	@Override
	protected boolean matchesSafely(Felhasznalo item, Description mismatchDescription) {
		return matches(nev, item.getNev(), "nev value: ", mismatchDescription) &&
				matches(jelszo, item.getJelszo(), "jelszo value: ", mismatchDescription) &&
				matches(szerep, item.getSzerep(), "szerep value: ", mismatchDescription) &&
				matches(szemelyitorzs, item.getSzemelyitorzs(), "szemelyitorzs value: ", mismatchDescription) &&
				matches(enabled, item.isEnabled(), "enabled value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(Felhasznalo.class.getSimpleName())
    	.appendText(", nev: ").appendDescriptionOf(nev)
    	.appendText(", jelszo: ").appendDescriptionOf(jelszo)
    	.appendText(", szerep: ").appendDescriptionOf(szerep)
    	.appendText(", szemelyitorzs: ").appendDescriptionOf(szemelyitorzs)
    	.appendText(", enabled: ").appendDescriptionOf(enabled);
	}
}
