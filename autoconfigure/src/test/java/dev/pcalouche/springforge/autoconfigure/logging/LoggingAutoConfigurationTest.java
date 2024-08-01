package dev.pcalouche.springforge.autoconfigure.logging;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class LoggingAutoConfigurationTest {

	private final ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner()
		.withConfiguration(AutoConfigurations.of(LoggingAutoConfiguration.class));

	@Test
	void loggingAutoConfiguration_WhenConditionalsAreMet_BeansAreCreated() {
		this.applicationContextRunner.withPropertyValues("springforge.logging.json-format=true").run(context -> {
			LoggingProperties loggingProperties = context.getBean(LoggingProperties.class);
			assertThat(loggingProperties.isJsonFormat()).isTrue();
		});
	}

}
