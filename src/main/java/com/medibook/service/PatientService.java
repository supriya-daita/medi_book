package com.medibook.service;

import com.medibook.exception.DuplicateEmailException;
import com.medibook.exception.PatientAlreadyInactiveException;
import com.medibook.exception.PatientNotFoundException;
import com.medibook.model.Patient;
import com.medibook.repository.PatientRepository;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service handling patient lifecycle operations, registration, status management, and discount calculations.
 * 
 * <p>Enforces unique email constraints, soft-deletion semantics for patient deactivation,
 * and age-bracket boundary rules for medical consultation discounts.</p>
 * 
 * Example usage:
 * <pre>
 *     PatientService patientService = new PatientService(patientRepository);
 *     Patient newPatient = patientService.registerPatient(new Patient("Alice", "alice@example.com", 30, "9876543210"));
 *     double discount = patientService.calculateDiscount(newPatient.getAge());
 * </pre>
 *
 * @author MediBook Team
 * @version 1.0
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    /**
     * Constructs a PatientService with constructor dependency injection.
     * 
     * @param patientRepository Spring Data JPA patient repository. Must not be null.
     */
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = Objects.requireNonNull(patientRepository, "PatientRepository must not be null");
    }

    /**
     * Registers a new patient in the system after validating uniqueness of email.
     *
     * @param patient The patient object to register. Must not be null. Email must be unique.
     * @return The saved Patient with generated database ID.
     * @throws IllegalArgumentException if patient is null or patient email is null/blank.
     * @throws DuplicateEmailException if the email is already registered in the system.
     */
    public Patient registerPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient object cannot be null");
        }
        if (patient.getEmail() == null || patient.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient email cannot be null or empty");
        }
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new DuplicateEmailException("A patient with email " + patient.getEmail() + " already exists");
        }
        return patientRepository.save(patient);
    }

    /**
     * Retrieves a patient by their unique database ID.
     *
     * @param patientId The ID of the patient. Must be a positive long greater than 0.
     * @return Optional containing the patient if found, empty Optional otherwise.
     * @throws IllegalArgumentException if patientId is null or less than or equal to 0.
     */
    public Optional<Patient> findPatientById(Long patientId) {
        if (patientId == null || patientId <= 0) {
            throw new IllegalArgumentException("Patient ID must be a positive non-null long");
        }
        return patientRepository.findById(patientId);
    }

    /**
     * Soft-deletes a patient by marking their active status as false.
     * All future appointments for this patient will be automatically cancelled.
     *
     * @param patientId The ID of the patient to deactivate. Must exist and currently be active.
     * @throws PatientNotFoundException if no patient with this ID exists in the database.
     * @throws PatientAlreadyInactiveException if the patient is already inactive.
     */
    @Transactional
    public void deactivatePatient(Long patientId) {
        Patient patient = findPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));

        if (!patient.isActive()) {
            throw new PatientAlreadyInactiveException("Patient with ID " + patientId + " is already inactive");
        }

        patient.setActive(false);
        patientRepository.save(patient);
    }

    /**
     * Calculates a discount percentage for the patient based on age.
     * 
     * <p>Business rules & BVA boundary brackets:
     * <ul>
     *   <li>Senior citizens (age &gt;= 60) receive a 20% discount (20.0).</li>
     *   <li>Children (age &lt;= 12) receive a 10% discount (10.0).</li>
     *   <li>All others (age 13 to 59) receive a 0% discount (0.0).</li>
     * </ul>
     * </p>
     *
     * @param age The patient's age in years. Must be between 0 (newborn) and 150.
     * @return Discount percentage as a double (0.0, 10.0, or 20.0).
     * @throws IllegalArgumentException if age is negative (&lt; 0) or greater than 150.
     */
    public double calculateDiscount(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age: " + age + ". Age must be between 0 and 150");
        }

        if (age <= 12) {
            return 10.0;
        } else if (age >= 60) {
            return 20.0;
        } else {
            return 0.0;
        }
    }
}
