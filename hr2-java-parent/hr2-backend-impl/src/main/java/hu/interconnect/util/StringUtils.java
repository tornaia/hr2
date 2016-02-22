package hu.interconnect.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class StringUtils {
	
	public static final String NEW_LINE = System.getProperty("line.separator");

	private static final Log LOG = LogFactory.getLog(StringUtils.class);
	
	public static String capitalizeFully(String s) {
		return WordUtils.capitalizeFully(s, new char[]{' ', '-'});
	}
	
	public static String toString(ByteArrayOutputStream baos) {
		try {
			return baos.toString(java.nio.charset.StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			LOG.error("Unable to convert ByteArrayOutputStream to String", e);
			return null;
		}
	}
	
	private StringUtils() {
	}
}
