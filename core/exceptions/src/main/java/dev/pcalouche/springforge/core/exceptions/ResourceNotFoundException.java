package dev.pcalouche.springforge.core.exceptions;

import lombok.experimental.StandardException;

/**
 * Exception to throw when a REST resource is cannot be found.
 */
@StandardException
public class ResourceNotFoundException extends RuntimeException {

}
