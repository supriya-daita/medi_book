package com.medibook.exception;

/**
 * Thrown when a requested patient cannot be found in the database.
 * 
 * @author MediBook Team
 * @version 1.0
 */
public class PatientNotFoundException extends RuntimeException {

    /**
     * Constructs a new PatientNotFoundException with a detailed error message.
     * 
     * @param message Detailed error description explaining which patient ID was missing.
     */
    public PatientNotFoundException(String message) {
        super(message);
    }
}
