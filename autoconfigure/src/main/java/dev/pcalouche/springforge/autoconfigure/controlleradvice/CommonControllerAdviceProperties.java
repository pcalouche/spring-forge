package dev.pcalouche.springforge.autoconfigure.controlleradvice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "springforge.common-controller-advice")
public class CommonControllerAdviceProperties {

	/**
	 * Enables common controller advice
	 */
	private boolean enabled = true;

}
