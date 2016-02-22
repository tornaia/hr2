package hu.interconnect.hr.backend.api.dto;

import java.util.List;

import hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus;

public class RendszerParameterekEditDTO {

	private List<RendszerParameterSaveDTO> rendszerParameterek;
	
	public List<RendszerParameterSaveDTO> getRendszerParameterek() {
		return rendszerParameterek;
	}

	public void setRendszerParameterek(List<RendszerParameterSaveDTO> rendszerParameterek) {
		this.rendszerParameterek = rendszerParameterek;
	}

	public static class RendszerParameterSaveDTO {
		
		private RendszerParameterTipus tipus;

		private String ertek;

		public RendszerParameterTipus getTipus() {
			return tipus;
		}

		public void setTipus(RendszerParameterTipus tipus) {
			this.tipus = tipus;
		}

		public String getErtek() {
			return ertek;
		}

		public void setErtek(String ertek) {
			this.ertek = ertek;
		}
	}
}
