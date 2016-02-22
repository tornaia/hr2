package hu.interconnect.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;

public final class LogbackConfigurerWithPropertySourcesPlaceholderConfigurer {

    public static void initLogging(String location, PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer) throws JoranException, IOException {
        String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
        URL url = ResourceUtils.getURL(resolvedLocation);
        String content = IOUtils.toString(url);
        
        PropertySources appliedPropertySources = propertySourcesPlaceholderConfigurer.getAppliedPropertySources();
        PropertySourcesPropertyResolver propertySourcesPropertyResolver = new PropertySourcesPropertyResolver(appliedPropertySources);
        String resolvedContent = propertySourcesPropertyResolver.resolvePlaceholders(content);
        
        File tempFile = File.createTempFile("logback.temp", ".xml");
        URI uri = tempFile.toURI();
        URL resolvedContentUrl = uri.toURL();
        IOUtils.write(resolvedContent, new FileOutputStream(tempFile));
        
        LoggerContext loggerContext = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();

        // in the current version logback automatically configures at startup the context, so we have to reset it
        loggerContext.reset();

        // reinitialize the logger context.  calling this method allows configuration through groovy or xml
        new ContextInitializer(loggerContext).configureByResource(resolvedContentUrl);
    }

    private LogbackConfigurerWithPropertySourcesPlaceholderConfigurer() {
    }
}
