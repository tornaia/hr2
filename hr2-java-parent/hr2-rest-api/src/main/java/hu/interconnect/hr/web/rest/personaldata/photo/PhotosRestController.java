package hu.interconnect.hr.web.rest.personaldata.photo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.FenykepCreateDTO;
import hu.interconnect.hr.backend.api.dto.FenykepekResponseDTO;
import hu.interconnect.hr.backend.api.dto.FenykepekSaveDTO;
import hu.interconnect.hr.backend.api.dto.FileResponseDTO;
import hu.interconnect.hr.backend.api.service.PhotosCommandService;
import hu.interconnect.hr.backend.api.service.PhotosQueryService;
import hu.interconnect.hr.web.rest.AbstractDownloadController;

@RestController
@RequestMapping("/api/v1/photos")
public class PhotosRestController extends AbstractDownloadController {
	
	private PhotosQueryService photosQueryService;
	
	private PhotosCommandService photosCommandService;

	@Autowired
	public PhotosRestController(PhotosQueryService photosQueryService, PhotosCommandService photosCommandService) {
		this.photosQueryService = photosQueryService;
		this.photosCommandService = photosCommandService;
	}
	
	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public byte[] get(@RequestParam int id) {
		return photosQueryService.get(id);
	}

	@RequestMapping(value = "/getdefault", method=RequestMethod.GET)
	public byte[] getMiniatur(@RequestParam int tsz) {
		return photosQueryService.getMiniatur(tsz);
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public FenykepekResponseDTO getFenykepDTOk(@RequestParam int tsz) {
		return photosQueryService.getFenykepDTOk(tsz);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody FenykepCreateDTO dto) {
		photosCommandService.create(dto);
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public void save(@RequestBody FenykepekSaveDTO dto) {
		photosCommandService.save(dto);
	}
	
	@RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public void create(@PathVariable int id) {
		photosCommandService.delete(id);
	}
	
	@RequestMapping(value = "/download", method=RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam int id) {
		FileResponseDTO filenameAndContent = photosQueryService.download(id);
		downloadFile(request, response, filenameAndContent);
	}
}
