package hu.interconnect.hr.backend.api.dto;

import java.util.List;

import hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus;

public class RendszerParameterekResponseDTO {

	public List<RendszerParameterDTO> rendszerParameterek;
	
	public RendszerParameterekResponseDTO(List<RendszerParameterDTO> rendszerParameterek) {
		this.rendszerParameterek = rendszerParameterek;
	}
	
	public static class RendszerParameterDTO {
		
		public RendszerParameterTipus tipus;

		public String ertek;
		
		public RendszerParameterDTO(RendszerParameterTipus tipus, String ertek) {
			this.tipus = tipus;
			this.ertek = ertek;
		}
	}
}
