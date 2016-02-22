package hu.interconnect.hr.backend.api.enumeration;

public enum RiportAllomanymod {
	
	OSSZES(null),
	AKTIV(Allomanymod.AKTIV),
	KILEPETT(Allomanymod.KILEPETT),
	GYESGYED(Allomanymod.GYESGYED);
	
	private Allomanymod allomanymod;
	
	RiportAllomanymod(Allomanymod allomanymod) {
		this.allomanymod = allomanymod;
	}
	
	public Allomanymod getAllomanymod() {
		return allomanymod;
	}
}
