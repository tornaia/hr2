package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

public class ApplicationInfoResponseDTO {

	public int buildNumber;
	
	public Date startDateTime;
	
	public ApplicationInfoResponseDTO(int buildNumber, Date startDateTime) {
		this.buildNumber = buildNumber;
		this.startDateTime = startDateTime;
	}
}
