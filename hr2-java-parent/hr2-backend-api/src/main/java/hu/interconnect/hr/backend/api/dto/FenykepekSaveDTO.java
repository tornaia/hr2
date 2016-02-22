package hu.interconnect.hr.backend.api.dto;

import java.util.List;

public class FenykepekSaveDTO {

	private List<FenykepSaveDTO> fenykepek;
	
	public List<FenykepSaveDTO> getFenykepek() {
		return fenykepek;
	}

	public void setFenykepek(List<FenykepSaveDTO> fenykepek) {
		this.fenykepek = fenykepek;
	}
	
	public static class FenykepSaveDTO {

		private int id;
		
		private String eredetiNev;
		
		private boolean miniatur;
		
		private byte[] adat;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getEredetiNev() {
			return eredetiNev;
		}

		public void setEredetiNev(String eredetiNev) {
			this.eredetiNev = eredetiNev;
		}

		public boolean isMiniatur() {
			return miniatur;
		}

		public void setMiniatur(boolean miniatur) {
			this.miniatur = miniatur;
		}

		public byte[] getAdat() {
			return adat;
		}

		public void setAdat(byte[] adat) {
			this.adat = adat;
		}
	}
}
