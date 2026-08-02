package com.medibook.exception;

/**
 * Thrown when an operation attempts to register a patient with an email address
 * that already exists in the system.
 * 
 * @author MediBook Team
 * @version 1.0
 */
public class DuplicateEmailException extends RuntimeException {

    /**
     * Constructs a new DuplicateEmailException with a detailed error message.
     * 
     * @param message Detailed message identifying the duplicated email address.
     */
    public DuplicateEmailException(String message) {
        super(message);
    }
}
