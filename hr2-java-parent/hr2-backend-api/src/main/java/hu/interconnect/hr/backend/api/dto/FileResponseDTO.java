package hu.interconnect.hr.backend.api.dto;

public class FileResponseDTO {

	public String filename;
	
	public byte[] content;

	public FileResponseDTO(String filename, byte[] content) {
		this.filename = filename;
		this.content = content;
	}
}
