package hu.interconnect.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.enumeration.Locale;

public final class Localizer {

	private static final Log LOGGER = LogFactory.getLog(Localizer.class);

	private static final char SEPARATOR = '/';
	private static final String BASE_DIR = "locales" + SEPARATOR;

	private static Map<String, Map<Locale, ResourceBundle>> bundles = new HashMap<>();

	// a singleton patternrol (illetve szinkronizalos ugyekrol) lasd itt : http://www.ibm.com/developerworks/java/library/j-dcl.html
	private static Localizer instance = new Localizer();

	private Localizer() {
		try {
			initialize();
		} catch (Exception e) {
			throw new ProgramozasiHiba("Nem sikerult a messages inicializalas", e);
		}
	}

	public static Localizer getInstance() {
		return instance;
	}

	private static void initialize() throws IOException {
		LOGGER.info("### Resource bundle-ok inicializalasanak kezdete");

		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resourcePatternResolver.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + SEPARATOR + BASE_DIR + Locale.hu_HU + SEPARATOR + "*.properties");

		for (Resource resource : resources) {
			String name = resource.getFilename().substring(0, resource.getFilename().lastIndexOf('.'));

			LOGGER.info("## Uj bundle hozzaadas: " + name);

			Map<Locale, ResourceBundle> localeBundles = new HashMap<>();
			bundles.put(name, localeBundles);

			for (Locale l : Locale.values()) {
				addBundle(l, BASE_DIR + l.name(), name, localeBundles);
			}
		}
		LOGGER.info("### Resource bundle-ok inicializalasanak vege");
	}

	private static void addBundle(Locale locale, String baseDir, String name, Map<Locale, ResourceBundle> localeBundles) {
		try {
			localeBundles.put(locale, ResourceBundle.getBundle(baseDir + SEPARATOR + name, locale.getJavaUtilLocale()));
		} catch (MissingResourceException e) {
			LOGGER.error("Nem letezik a resource bundle " + locale + " nyelven : " + name);
			throw new ProgramozasiHiba("Nem letezik a resource bundle " + locale + " nyelven : " + name, e);
		}
	}

	public static String getMessage(Enum<?> e) {
		return getMessage("enum", e.getClass().getSimpleName() + "." + e.toString());
	}

	public static String getMessage(String bundle, String msgkey) {
		Locale locale = FelhasznaloUtils.aktualisFelhasznalo().getLocale();
		return getMessage(bundle, msgkey, locale);
	}
	
	private static String getMessage(String bundle, String messageKey, Locale locale) {
		return getRawMessage(bundle, messageKey, locale);
	}

	private static String getRawMessage(String bundle, String messageKey, Locale locale) {
		ResourceBundle resourceBundle = getBundle(bundle, locale);

		if (resourceBundle == null) {
			return null;
		}

		try {
			return resourceBundle.getString(messageKey);
		} catch (MissingResourceException e) {
			LOGGER.info("Nem letezik a message: " + bundle + "." + messageKey);
			return null;
		}
	}
	
	private static ResourceBundle getBundle(String bundle, Locale locale) {
		Map<Locale, ResourceBundle> map = bundles.get(bundle);

		if (map == null) {
			LOGGER.error("Nem letezik a bundle: " + bundle);
			return null;
		}

		ResourceBundle resourceBundle = map.get(locale);

		if (resourceBundle == null) {
			LOGGER.error("Nem letezik a bundle: " + bundle);
			return null;
		}

		return resourceBundle;
	}
}
