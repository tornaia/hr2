package hu.interconnect.util;

import java.util.Objects;

public final class ObjectUtils {

	public static boolean equalsAndNotNull(Object a, Object b) {
		return Objects.equals(a, b) && Objects.nonNull(a);
	}
	
	private ObjectUtils() {
	}
}
