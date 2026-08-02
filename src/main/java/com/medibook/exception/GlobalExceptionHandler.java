package com.medibook.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller Advice capturing unhandled exceptions across REST endpoints
 * and returning standardized JSON error structures.
 * 
 * @author MediBook Team
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link PatientNotFoundException} thrown when a requested patient does not exist.
     * 
     * @param ex The caught PatientNotFoundException instance containing error details. Must not be null.
     * @return ResponseEntity containing a 404 Not Found HTTP status and JSON error payload.
     */
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePatientNotFound(PatientNotFoundException ex) {
        Map<String, Object> body = createErrorBody(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /**
     * Handles {@link DuplicateEmailException} thrown when registering a patient with an already existing email address.
     * 
     * @param ex The caught DuplicateEmailException instance containing duplicate email details. Must not be null.
     * @return ResponseEntity containing a 409 Conflict HTTP status and JSON error payload.
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEmail(DuplicateEmailException ex) {
        Map<String, Object> body = createErrorBody(HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    /**
     * Handles {@link PatientAlreadyInactiveException} thrown when attempting to deactivate an already inactive patient.
     * 
     * @param ex The caught PatientAlreadyInactiveException instance. Must not be null.
     * @return ResponseEntity containing a 400 Bad Request HTTP status and JSON error payload.
     */
    @ExceptionHandler(PatientAlreadyInactiveException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyInactive(PatientAlreadyInactiveException ex) {
        Map<String, Object> body = createErrorBody(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Handles {@link IllegalArgumentException} thrown for invalid method arguments or boundary violations.
     * 
     * @param ex The caught IllegalArgumentException instance. Must not be null.
     * @return ResponseEntity containing a 400 Bad Request HTTP status and JSON error payload.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> body = createErrorBody(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Handles {@link MethodArgumentNotValidException} thrown when Bean Validation annotations on DTOs fail.
     * 
     * @param ex The caught MethodArgumentNotValidException containing binding validation errors. Must not be null.
     * @return ResponseEntity containing a 400 Bad Request HTTP status and field-level validation error map.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Failed");
        body.put("details", errors);

        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Helper method to build a standardized error response map.
     * 
     * @param status HTTP status code integer.
     * @param error HTTP status error title.
     * @param message Detailed error message explanation.
     * @return Map containing timestamp, status, error, and message fields.
     */
    private Map<String, Object> createErrorBody(int status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        body.put("error", error);
        body.put("message", message);
        return body;
    }
}

