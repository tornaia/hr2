package hu.interconnect.hr.module.messages;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.service.MessagesQueryService;

@Component
public class MessagesQueryServiceImpl implements MessagesQueryService {

	private static final Log LOG = LogFactory.getLog(MessagesQueryServiceImpl.class);

	private static final Map<Locale, Map<String, String>> locales = Maps.newHashMap();

	@PostConstruct
	public void init() throws IOException {
		for (Locale locale : Locale.values()) {
			locales.put(locale, loadMessages(locale));
		}
	}

	@Override
	public Map<String, String> getMessages(Locale locale) {
		return locales.get(locale);
	}

	private static Map<String, String> loadMessages(Locale locale) throws IOException {
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		Resource[] packages = pathMatchingResourcePatternResolver.getResources("classpath:/locales/" + locale + "/*.properties");

		Map<String, String> mergedProperties = Maps.newHashMap();
		for (Resource pkg : packages) {
			File fileToLoad = new File(pkg.getURL().getFile());
			String bundle = FilenameUtils.getBaseName(fileToLoad.getName());
			LOG.debug("Loading " + fileToLoad.getAbsolutePath() + " as bundle: " + bundle);
			Properties properties = new Properties();
			try (InputStream filetoLoadIS = pkg.getInputStream()) {
				properties.load(filetoLoadIS);
				for (Map.Entry<Object, Object> entry : properties.entrySet()) {
					String key = bundle + "." + entry.getKey().toString();
					String value = entry.getValue().toString();
					mergedProperties.put(key, value);
					LOG.trace("Found '" + key + "' with value '" + value + "'");
				}
				LOG.debug("Found " + properties.entrySet().size() + " entries");
			}
		}
		
		return mergedProperties;
	}
}
