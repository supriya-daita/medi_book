package com.medibook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the MediBook Hospital Appointment Management System.
 * 
 * <p>MediBook is a production-grade Spring Boot application designed to validate
 * automated test generation capabilities across unit, integration, BVA, and mock
 * test scenarios.</p>
 * 
 * Example usage:
 * <pre>
 *     SpringApplication.run(MediBookApplication.class, args);
 * </pre>
 * 
 * @author MediBook Team
 * @version 1.0
 */
@SpringBootApplication
public class MediBookApplication {

    /**
     * Standard Java main method to launch the Spring Boot application.
     * 
     * @param args Command line arguments passed during application startup.
     */
    public static void main(String[] args) {
        SpringApplication.run(MediBookApplication.class, args);
    }
}
