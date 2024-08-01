package dev.pcalouche.springforge.libs.exceptions;

import lombok.experimental.StandardException;

/**
 * Exception to throw when an action cannot be taken on a REST resource because it
 * conflicts with its current state.
 */
@StandardException
public class ResourceConflictException extends RuntimeException {

}
