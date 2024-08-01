package dev.pcalouche.springforge.autoconfigure.controlleradvice;

import static org.assertj.core.api.Assertions.assertThat;

import dev.pcalouche.springforge.libs.exceptions.ResourceConflictException;
import dev.pcalouche.springforge.libs.exceptions.ResourceForbiddenException;
import dev.pcalouche.springforge.libs.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;

public class CommonControllerAdviceTest {

	private final CommonControllerAdvice commonControllerAdvice = new CommonControllerAdvice();

	@Test
	void handleNotFoundException_WithResourceNotFoundException_CreatesExpectedProblemDetail() {
		ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("not found");
		assertThat(commonControllerAdvice.handleNotFoundException(resourceNotFoundException))
			.isInstanceOf(ProblemDetail.class)
			.hasFieldOrPropertyWithValue("detail", "not found")
			.hasFieldOrPropertyWithValue("status", 404);
	}

	@Test
	void handleForbiddenException_WithResourceForbiddenException_CreatesExpectedProblemDetail() {
		ResourceForbiddenException resourceForbiddenException = new ResourceForbiddenException("forbidden");
		assertThat(commonControllerAdvice.handleForbiddenException(resourceForbiddenException))
			.isInstanceOf(ProblemDetail.class)
			.hasFieldOrPropertyWithValue("detail", "forbidden")
			.hasFieldOrPropertyWithValue("status", 403);
		;
	}

	@Test
	void handleConflictException_WithResourceConflictException_CreatesExpectedProblemDetail() {
		ResourceConflictException resourceConflictException = new ResourceConflictException("conflict");
		assertThat(commonControllerAdvice.handleConflictException(resourceConflictException))
			.isInstanceOf(ProblemDetail.class)
			.hasFieldOrPropertyWithValue("detail", "conflict")
			.hasFieldOrPropertyWithValue("status", 409);
		;
	}

}
