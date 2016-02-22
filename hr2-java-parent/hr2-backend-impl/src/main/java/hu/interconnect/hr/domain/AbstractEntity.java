package hu.interconnect.hr.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

	public abstract Integer getId();

	@Override
	public final int hashCode() {
		return super.hashCode();
	}

	@Override
	public final boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public final String toString() {
		return super.toString();
	}
}