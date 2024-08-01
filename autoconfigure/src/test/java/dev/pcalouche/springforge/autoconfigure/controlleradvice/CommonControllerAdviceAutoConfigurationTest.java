package dev.pcalouche.springforge.autoconfigure.controlleradvice;

import static dev.pcalouche.springforge.autoconfigure.controlleradvice.CommonControllerAdviceAutoConfiguration.COMMON_CONTROLLER_ADVICE_BEAN_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

public class CommonControllerAdviceAutoConfigurationTest {

	private final WebApplicationContextRunner webApplicationContextRunner = new WebApplicationContextRunner()
		.withConfiguration(AutoConfigurations.of(CommonControllerAdviceAutoConfiguration.class));

	@Test
	void commonControllerAdviceAutoConfiguration_WhenConditionalsAreMet_BeansAreCreated() {
		webApplicationContextRunner.run(context -> {
			var controllerAdviceProperties = context.getBean(CommonControllerAdviceProperties.class);
			assertThat(controllerAdviceProperties.isEnabled()).isTrue();
			assertThat(context).hasSingleBean(CommonControllerAdvice.class);
			assertThat(context).hasBean(COMMON_CONTROLLER_ADVICE_BEAN_NAME);
		});
	}

	@Test
	void commonControllerAdviceAutoConfiguration_WhenDisabled_BeansAreNotCreated() {
		webApplicationContextRunner.withPropertyValues("springforge.common-controller-advice.enabled=false")
			.run(context -> {
				assertThat(context).doesNotHaveBean(CommonControllerAdviceProperties.class);
				assertThat(context).doesNotHaveBean(COMMON_CONTROLLER_ADVICE_BEAN_NAME);
			});
	}

	@Test
	void commonControllerAdviceAutoConfiguration_WhenNoWebContext_BeansAreNotCreated() {
		ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(CommonControllerAdviceAutoConfiguration.class));

		applicationContextRunner.withPropertyValues("springforge.common-controller-advice.enabled=false")
			.run(context -> {
				assertThat(context).doesNotHaveBean(CommonControllerAdviceProperties.class);
				assertThat(context).doesNotHaveBean(COMMON_CONTROLLER_ADVICE_BEAN_NAME);
			});
	}

}
