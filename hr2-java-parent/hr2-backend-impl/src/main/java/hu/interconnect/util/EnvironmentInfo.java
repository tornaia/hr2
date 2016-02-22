package hu.interconnect.util;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public final class EnvironmentInfo {

    private static final Log LOG = LogFactory.getLog(EnvironmentInfo.class);

	public static void logAllEnvironmentAndJVMVariables() {
        for (Map.Entry<String, String> envEntry : new TreeMap<>(System.getenv()).entrySet()) {
        	LOG.info("Environment variable >>> " + envEntry.getKey() + " = " + envEntry.getValue());
        }
        
        for (Map.Entry<Object, Object> jvmEntry : new TreeMap<>(System.getProperties()).entrySet()) {
        	LOG.info("JVM variable >>> " + jvmEntry.getKey().toString() + " = " + jvmEntry.getValue().toString());
        }
	}
	
    private EnvironmentInfo() {
    	logAllEnvironmentAndJVMVariables();
    }
}
