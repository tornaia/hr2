package hu.interconnect.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import hu.interconnect.exception.ProgramozasiHiba;

public final class VelocityUtils {
	
	public static String generate(String template, Map<String, Object> context) {
		Writer sw = new StringWriter();
		Velocity.setProperty("runtime.log.logsystem.log4j.logger", "root");
		boolean succeed = Velocity.evaluate(new VelocityContext(context), sw, "", template);
		if (!succeed) {
			throw new ProgramozasiHiba(String.format("Evaluation of template %s with context %s failed", template, context));
		}
		return sw.toString();
	}
	
	private VelocityUtils() {
	}
}
