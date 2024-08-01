package dev.pcalouche.springforge.autoconfigure.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(LoggingProperties.class)
@Slf4j
public class LoggingAutoConfiguration {

	public LoggingAutoConfiguration() {
		log.info("Applying Spring Forge Logging Auto Configuration.");
	}

}
