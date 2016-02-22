package hu.interconnect.hr.backend.api.dto;

import java.util.List;

public class FenykepekResponseDTO {

	public List<FenykepResponseDTO> fenykepek;
	
	public FenykepekResponseDTO(List<FenykepResponseDTO> fenykepek) {
		this.fenykepek = fenykepek;
	}
	
	public static class FenykepResponseDTO {
		
		public Integer id;
		
		public String eredetiNev;
		
		public boolean miniatur;
		
		public FenykepResponseDTO(Integer id, String eredetiNev, boolean miniatur) {
			this.id = id;
			this.eredetiNev = eredetiNev;
			this.miniatur = miniatur;
		}
	}
}
