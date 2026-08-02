package com.medibook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for patient registration requests.
 * 
 * @author MediBook Team
 * @version 1.0
 */
public class PatientRegistrationRequest {

    @NotBlank(message = "Patient name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Email address cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "Age cannot exceed 150 years")
    private int age;

    @Pattern(regexp = "\\d{10}", message = "Phone must be exactly 10 digits")
    private String phone;

    @Size(max = 1000, message = "Medical history cannot exceed 1000 characters")
    private String medicalHistory;

    /** Default no-argument constructor for JSON deserialization. */
    public PatientRegistrationRequest() {
    }

    /**
     * Parameterized constructor for creating a PatientRegistrationRequest DTO.
     * 
     * @param name Full patient name.
     * @param email Unique patient email.
     * @param age Patient age (0 to 150).
     * @param phone 10-digit phone string.
     * @param medicalHistory Optional medical history notes.
     */
    public PatientRegistrationRequest(String name, String email, int age, String phone, String medicalHistory) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.medicalHistory = medicalHistory;
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
     * Gets 10-digit phone string.
     * 
     * @return Phone string.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets 10-digit phone string.
     * 
     * @param phone Phone string.
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
