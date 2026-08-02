package com.medibook.controller;

import com.medibook.dto.PatientRegistrationRequest;
import com.medibook.dto.PatientResponse;
import com.medibook.exception.PatientNotFoundException;
import com.medibook.model.Patient;
import com.medibook.service.PatientService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller exposing patient management APIs.
 * 
 * <p>Provides endpoints for patient registration, fetching patient records by ID,
 * deactivating patient accounts, and evaluating age-based discount percentages.</p>
 * 
 * @author MediBook Team
 * @version 1.0
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    /**
     * Constructor injection for PatientController.
     * 
     * @param patientService Service managing patient logic. Must not be null.
     */
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Registers a new patient.
     * 
     * @param request Validated patient registration payload.
     * @return 201 Created with PatientResponse payload and Location header.
     */
    @PostMapping
    public ResponseEntity<PatientResponse> registerPatient(@Valid @RequestBody PatientRegistrationRequest request) {
        Patient patient = new Patient(request.getName(), request.getEmail(), request.getAge(), request.getPhone());
        patient.setMedicalHistory(request.getMedicalHistory());

        Patient saved = patientService.registerPatient(patient);
        return ResponseEntity
                .created(URI.create("/api/patients/" + saved.getId()))
                .body(new PatientResponse(saved));
    }

    /**
     * Retrieves a patient by ID.
     * 
     * @param id Patient database ID.
     * @return 200 OK with PatientResponse if found.
     * @throws PatientNotFoundException if patient with given ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.findPatientById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));
        return ResponseEntity.ok(new PatientResponse(patient));
    }

    /**
     * Deactivates a patient account by ID.
     * 
     * @param id Patient database ID.
     * @return 200 OK with success confirmation message.
     */
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, String>> deactivatePatient(@PathVariable Long id) {
        patientService.deactivatePatient(id);
        return ResponseEntity.ok(Map.of("message", "Patient with ID " + id + " successfully deactivated"));
    }

    /**
     * Calculates the discount percentage applicable for a given age.
     * 
     * @param age Patient age (0 to 150).
     * @return 200 OK with discount percentage payload.
     */
    @GetMapping("/discount")
    public ResponseEntity<Map<String, Object>> calculateDiscount(@RequestParam int age) {
        double discount = patientService.calculateDiscount(age);
        return ResponseEntity.ok(Map.of(
            "age", age,
            "discountPercent", discount
        ));
    }
}
