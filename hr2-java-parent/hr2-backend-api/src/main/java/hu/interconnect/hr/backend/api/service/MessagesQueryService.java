package hu.interconnect.hr.backend.api.service;

import java.util.Map;

import hu.interconnect.hr.backend.api.enumeration.Locale;

public interface MessagesQueryService {

	Map<String, String> getMessages(Locale locale);
	
}
