package hu.interconnect.hr.web.rest.personaldata.receiveditem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.AtvettEszkozCreateDTO;
import hu.interconnect.hr.backend.api.dto.AtvettEszkozEditDTO;
import hu.interconnect.hr.backend.api.dto.AtvettEszkozokResponseDTO;
import hu.interconnect.hr.backend.api.dto.FileResponseDTO;
import hu.interconnect.hr.backend.api.service.ReceiveditemsCommandService;
import hu.interconnect.hr.backend.api.service.ReceiveditemsQueryService;
import hu.interconnect.hr.web.rest.AbstractDownloadController;

@RestController
@RequestMapping("/api/v1/receiveditems")
public class ReceiveditemsRestController extends AbstractDownloadController {

	private ReceiveditemsQueryService receiveditemsQueryService;
	
	private ReceiveditemsCommandService receiveditemsCommandService;

	@Autowired
	public ReceiveditemsRestController(ReceiveditemsQueryService receiveditemsQueryService, ReceiveditemsCommandService receiveditemsCommandService) {
		this.receiveditemsQueryService = receiveditemsQueryService;
		this.receiveditemsCommandService = receiveditemsCommandService;
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public AtvettEszkozokResponseDTO findAll(@RequestParam int tsz) {
		return receiveditemsQueryService.findAllByTsz(tsz);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void create(@RequestBody AtvettEszkozCreateDTO dto) {
		receiveditemsCommandService.create(dto);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void edit(@RequestBody AtvettEszkozEditDTO dto) {
		receiveditemsCommandService.edit(dto);
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(@RequestParam int id, HttpServletRequest request, HttpServletResponse response) {
		FileResponseDTO filenameAndContent = receiveditemsQueryService.download(id);
		downloadFile(request, response, filenameAndContent);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void delete(@PathVariable int id) {
		receiveditemsCommandService.delete(id);
	}
}
