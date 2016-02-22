package hu.interconnect.hr.backend.api.enumeration;

public enum JelenletiAdatTipus {
	
	MUNKA(true),
	SZABADNAP(false),
	SZABADSAG(false),
	BETEGSZABADSAG(false),
	TEMETESI_SZABADSAG(false),
	APA_SZABADSAG(false),
	TANULMANYI_SZABADSAG(false);
	
	private boolean toltheto;
	
	JelenletiAdatTipus(boolean toltheto) {
		this.toltheto = toltheto;
	}
	
	public boolean isToltheto() {
		return toltheto;
	}
}
