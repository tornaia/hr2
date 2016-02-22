package hu.interconnect.hr.web.rest.personaldata.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.MegjegyzesResponseDTO;
import hu.interconnect.hr.backend.api.dto.MegjegyzesSaveDTO;
import hu.interconnect.hr.backend.api.service.CommentCommandService;
import hu.interconnect.hr.backend.api.service.CommentQueryService;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentRestController {

	private CommentQueryService commentQueryService;
	
	private CommentCommandService commentCommandService;

	@Autowired
	public CommentRestController(CommentQueryService commentQueryService, CommentCommandService commentCommandService) {
		this.commentQueryService = commentQueryService;
		this.commentCommandService = commentCommandService;
	}
	
	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public MegjegyzesResponseDTO getMegjegyzes(@RequestParam int tsz) {
		return commentQueryService.getMegjegyzes(tsz);
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public void save(@RequestBody MegjegyzesSaveDTO dto) {
		commentCommandService.save(dto);
	}
}
