package test.matcher;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class MapMatcher<K, V> extends TypeSafeDiagnosingMatcher<Map<K, V>> {

	private List<SimpleEntry<String, Object>> expectedEntries = newArrayList();
	
	public MapMatcher<K,V> adottKulcsErtekParral(String kulcs, Object ertek) {
		expectedEntries.add(new SimpleEntry<>(kulcs, ertek));
		return this;
	}
	
	@Override
	protected boolean matchesSafely(Map<K, V> item, Description mismatchDescription) {
		Matcher<Collection<? extends Object>> hasSize = hasSize(expectedEntries.size());
		if (!hasSize.matches(item.entrySet())) {
			mismatchDescription.appendText(" szamossag ");
			hasSize.describeMismatch(item.size(), mismatchDescription);
			return false;
		}
		
		for (SimpleEntry<String, Object> entry : expectedEntries) {
			Matcher<Map<? extends String,? extends Object>> hasEntryMatcher = hasEntry(entry.getKey(), entry.getValue());
			
			if (!hasEntryMatcher.matches(item)) {
				mismatchDescription.appendText(" kulcs-ertek hianyzik ");
				hasEntryMatcher.describeMismatch(item.entrySet(), mismatchDescription);
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("( ");
		for (SimpleEntry<String, Object> entry : expectedEntries) {
			Matcher<Map<? extends String, ? extends Object>> hasEntry = hasEntry(entry.getKey(), entry.getValue());
			description.appendText("kulcs-ertek ")
						.appendDescriptionOf(hasEntry);
		}
		description.appendText(") ");
	}

}
