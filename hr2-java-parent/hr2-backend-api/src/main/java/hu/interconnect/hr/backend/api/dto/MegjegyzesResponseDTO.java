package hu.interconnect.hr.backend.api.dto;

public final class MegjegyzesResponseDTO {

	public int tsz;
	
	public String szoveg;
	
	private MegjegyzesResponseDTO(int tsz, String szoveg) {
		this.tsz = tsz;
		this.szoveg = szoveg;
	}

	public static MegjegyzesResponseDTO ures(int tsz) {
		return new MegjegyzesResponseDTO(tsz, "");
	}

	public static MegjegyzesResponseDTO letrehoz(int tsz, String szoveg) {
		return new MegjegyzesResponseDTO(tsz, szoveg);
	}
}
