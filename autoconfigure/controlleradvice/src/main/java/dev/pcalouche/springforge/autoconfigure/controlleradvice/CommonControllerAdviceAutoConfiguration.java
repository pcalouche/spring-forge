package dev.pcalouche.springforge.autoconfigure.controlleradvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springforge.common-controller-advice", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(CommonControllerAdviceProperties.class)
@Slf4j
public class CommonControllerAdviceAutoConfiguration {

	public static final String COMMON_CONTROLLER_ADVICE_BEAN_NAME = "commonControllerAdvice";

	public CommonControllerAdviceAutoConfiguration() {
		log.info("Applying Spring Forge Common Controller Advice Auto Configuration.");
	}

	@Bean
	public CommonControllerAdvice commonControllerAdvice() {
		return new CommonControllerAdvice();
	}

}
