package hu.interconnect.hr.web.rest.applicationinfo;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.ApplicationInfoResponseDTO;
import hu.interconnect.util.DateUtils;
import hu.interconnect.util.FileUtils;

@RestController
@RequestMapping("/api/v1/applicationinfo")
public class ApplicationInfoRestController {
	
	private static final Log LOG = LogFactory.getLog(ApplicationInfoRestController.class);

	private Integer buildNumber;
	
	private Date startDateTime;
	
	@PostConstruct
	public void szerverVerziotBeallit() throws IOException {
		Properties properties = new Properties();
		properties.load(FileUtils.getClasspathInputStream("/build.properties"));
		String buildNumberStr = properties.getProperty("build.number");
		buildNumber = Integer.valueOf(buildNumberStr);
		startDateTime = DateUtils.aktualisIdo();
		LOG.info("A szerver verzioja: " + buildNumber);
	}
	
	@RequestMapping
	public ApplicationInfoResponseDTO getBuildNumber() {
		return new ApplicationInfoResponseDTO(buildNumber, startDateTime);
	}
}
