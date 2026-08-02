package com.medibook.repository;

import com.medibook.model.Patient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA Repository for managing {@link Patient} entity persistence.
 * 
 * @author MediBook Team
 * @version 1.0
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Finds a patient by unique email address.
     * 
     * @param email The email address string to search for. Must not be null.
     * @return Optional containing Patient if found, empty Optional otherwise.
     */
    Optional<Patient> findByEmail(String email);

    /**
     * Checks if a patient with the given email address already exists in the database.
     * 
     * @param email Email address string.
     * @return True if a patient record exists with this email, false otherwise.
     */
    boolean existsByEmail(String email);
}
