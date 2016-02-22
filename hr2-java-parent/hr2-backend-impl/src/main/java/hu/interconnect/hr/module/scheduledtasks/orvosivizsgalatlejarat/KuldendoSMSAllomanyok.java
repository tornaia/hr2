package hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.unmodifiableList;
import hu.interconnect.hr.module.scheduledtasks.KuldendoSMSAllomany;

import java.util.Iterator;
import java.util.List;

public class KuldendoSMSAllomanyok implements Iterable<KuldendoSMSAllomany> {

	private List<KuldendoSMSAllomany> kuldendoSMSAllomanyok = newArrayList();

	public void add(KuldendoSMSAllomany kuldendoSMSAllomany) {
		kuldendoSMSAllomanyok.add(kuldendoSMSAllomany);
	}
	
	public boolean isEmpty() {
		return kuldendoSMSAllomanyok.isEmpty();
	}
	
	@Override
	public Iterator<KuldendoSMSAllomany> iterator() {
		return unmodifiableList(kuldendoSMSAllomanyok).iterator();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("KuldendoSMSAllomanyok (").append(kuldendoSMSAllomanyok.size()).append(") {");
		Iterator<KuldendoSMSAllomany> iterator = kuldendoSMSAllomanyok.iterator();
		while (iterator.hasNext()) {
			sb.append(iterator.next());
			if (iterator.hasNext()) {
				sb.append("; ");
			}
		}
		return sb.append("}").toString();
	}
	
}
