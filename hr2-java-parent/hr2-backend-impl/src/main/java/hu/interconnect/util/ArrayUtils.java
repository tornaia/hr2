package hu.interconnect.util;

import static java.util.Arrays.stream;

import org.apache.commons.lang.StringUtils;

public final class ArrayUtils {
	
	public static String[] removeNullOrEmptyElements(String[] array) {
		return stream(array)
				.filter(StringUtils::isNotBlank)
				.toArray(size -> new String[size]);
	}
	
	private ArrayUtils() {
	}
}
