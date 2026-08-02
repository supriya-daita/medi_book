package com.medibook.exception;

/**
 * Thrown when attempting to deactivate a patient account that is already marked as inactive.
 * 
 * @author MediBook Team
 * @version 1.0
 */
public class PatientAlreadyInactiveException extends RuntimeException {

    /**
     * Constructs a new PatientAlreadyInactiveException with a detailed error message.
     * 
     * @param message Description of the patient deactivation failure.
     */
    public PatientAlreadyInactiveException(String message) {
        super(message);
    }
}
