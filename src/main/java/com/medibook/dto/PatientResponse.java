package com.medibook.dto;

import com.medibook.model.Patient;

/**
 * Data Transfer Object (DTO) representing a serialized Patient response.
 * 
 * @author MediBook Team
 * @version 1.0
 */
public class PatientResponse {

    private Long id;
    private String name;
    private String email;
    private int age;
    private String phone;
    private boolean active;
    private String medicalHistory;

    /** Default no-argument constructor for JSON serialization. */
    public PatientResponse() {
    }

    /**
     * Constructs a PatientResponse DTO from a Patient domain entity.
     * 
     * @param patient Source Patient entity instance. Must not be null.
     */
    public PatientResponse(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.email = patient.getEmail();
        this.age = patient.getAge();
        this.phone = patient.getPhone();
        this.active = patient.isActive();
        this.medicalHistory = patient.getMedicalHistory();
    }

    /**
     * Gets patient ID.
     * 
     * @return Patient database ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets patient ID.
     * 
     * @param id Patient database ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets patient name.
     * 
     * @return Name string.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets patient name.
     * 
     * @param name Name string.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets patient email.
     * 
     * @return Email string.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets patient email.
     * 
     * @param email Email string.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets patient age.
     * 
     * @return Age integer.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets patient age.
     * 
     * @param age Age integer.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets phone number.
     * 
     * @return Phone string.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone number.
     * 
     * @param phone Phone string.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Checks if patient is active.
     * 
     * @return Active boolean flag.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active status.
     * 
     * @param active Active boolean flag.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets medical history notes.
     * 
     * @return Medical history string.
     */
    public String getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets medical history notes.
     * 
     * @param medicalHistory Medical history string.
     */
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
