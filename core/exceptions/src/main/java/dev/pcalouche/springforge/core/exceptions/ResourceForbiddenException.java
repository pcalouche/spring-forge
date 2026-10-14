package dev.pcalouche.springforge.core.exceptions;

import lombok.experimental.StandardException;

/**
 * Exception to throw when a REST resource cannot be accessed or deleted.
 */
@StandardException
public class ResourceForbiddenException extends RuntimeException {

}
