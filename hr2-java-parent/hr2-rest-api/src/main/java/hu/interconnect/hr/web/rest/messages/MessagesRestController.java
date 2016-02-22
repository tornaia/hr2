package hu.interconnect.hr.web.rest.messages;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.service.MessagesQueryService;

@RestController
@RequestMapping("/api/v1/messages")
public class MessagesRestController {
	
	@Autowired
	private MessagesQueryService messagesQueryService;

    @RequestMapping(value = "/{locale}", method = RequestMethod.GET)
    public Map<String, String> getMessages(@PathVariable Locale locale) {
    	return messagesQueryService.getMessages(locale);
    }
}