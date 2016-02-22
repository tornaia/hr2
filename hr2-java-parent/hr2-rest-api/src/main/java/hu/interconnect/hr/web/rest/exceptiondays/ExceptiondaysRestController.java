package hu.interconnect.hr.web.rest.exceptiondays;

import static hu.interconnect.util.DateUtils.HONAP_FORMATUM;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.KivetelnapCreateDTO;
import hu.interconnect.hr.backend.api.dto.KivetelnapokResponseDTO;
import hu.interconnect.hr.backend.api.service.ExceptiondaysCommandService;
import hu.interconnect.hr.backend.api.service.ExceptiondaysQueryService;

@RestController
@RequestMapping("/api/v1/exceptiondays")
public class ExceptiondaysRestController {

	private ExceptiondaysQueryService exceptiondaysQueryService;
	
	private ExceptiondaysCommandService exceptiondaysCommandService;
	
	@Autowired
	public ExceptiondaysRestController(ExceptiondaysQueryService exceptiondaysQueryService, ExceptiondaysCommandService exceptiondaysCommandService) {
		this.exceptiondaysQueryService = exceptiondaysQueryService;
		this.exceptiondaysCommandService = exceptiondaysCommandService;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public KivetelnapokResponseDTO getall() {
		return exceptiondaysQueryService.getOsszesKivetelnap();
	}
	
	@RequestMapping(value = "/findbymonth", method=RequestMethod.GET)
	public KivetelnapokResponseDTO findbymonth(@RequestParam @DateTimeFormat(pattern=HONAP_FORMATUM) Date month) {
		return exceptiondaysQueryService.getOsszesKivetelnap(month);
	}

	@RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public void delete(@PathVariable int id) {
		exceptiondaysCommandService.delete(id);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody KivetelnapCreateDTO dto) {
		exceptiondaysCommandService.create(dto);
	}
}
