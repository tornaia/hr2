package hu.interconnect.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

public final class LoggingInterceptor {
	
	private static final Log LOG = LogFactory.getLog(LoggingInterceptor.class);

	public static void hivasElott(JoinPoint jp) {
		try {
			loggerFor(jp).info(jp.getTarget().getClass().getSimpleName() + " - " + jp.getSignature().getName() + "(" + StringUtils.join(jp.getArgs(), ",") + ")");
		} catch (Exception e) {
			LOG.error("Logging failed", e);
			loggerFor(jp).error(jp.getTarget().getClass().getSimpleName() + " - " + jp.getSignature().getName() + "(???)");
		}
	}

	public static void hibaEseten(JoinPoint jp, Throwable ex) {
		loggerFor(jp).info("Exception in method: " + jp.getTarget().getClass().getSimpleName() + " - " + jp.getSignature().getName() + ", exception: " + ex.getClass() + ", message: " + ex.getMessage());
	}

	private static Log loggerFor(JoinPoint jp) {
		return LogFactory.getLog(jp.getTarget().getClass());
	}
	
	private LoggingInterceptor() {
	}
}