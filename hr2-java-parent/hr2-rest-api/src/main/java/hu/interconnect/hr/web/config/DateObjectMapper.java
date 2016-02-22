package hu.interconnect.hr.web.config;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Service
public class DateObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;
 
	public DateObjectMapper() {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        registerModule(new JodaModule());
    }
}