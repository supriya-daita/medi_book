package com.medibook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;

/**
 * Represents a registered patient in the MediBook system.
 * 
 * <p>Patients must be between 0 (newborn) and 150 years of age.
 * Email address must be unique across all patients in the system.
 * Phone number must consist of exactly 10 numeric digits.</p>
 * 
 * Example usage:
 * <pre>
 *     Patient patient = new Patient("John Doe", "john@example.com", 30, "9876543210");
 *     patient.setMedicalHistory("No known allergies");
 * </pre>
 * 
 * @author MediBook Team
 * @version 1.0
 */
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Full legal name of the patient. Must not be blank (2 to 100 characters). */
    @NotBlank(message = "Patient name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    /** Unique email address used for notifications and identification. */
    @NotNull(message = "Email address cannot be null")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;

    /** Patient age in years. Must be between 0 (newborn) and 150. */
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "Age cannot exceed 150 years")
    private int age;

    /** Phone number: exactly 10 numeric digits. */
    @Pattern(regexp = "\\d{10}", message = "Phone must be exactly 10 digits")
    private String phone;

    /** Whether this patient account is active. Soft-delete flag. */
    private boolean active = true;

    /** Brief medical history notes. Optional. Maximum 1000 characters. */
    @Size(max = 1000, message = "Medical history cannot exceed 1000 characters")
    private String medicalHistory;

    /** Default no-argument constructor required by JPA. */
    public Patient() {
    }

    /**
     * Parameterized constructor for creating a Patient instance.
     * 
     * @param name Full legal name of the patient. Must not be blank.
     * @param email Unique email address. Must be a valid email format.
     * @param age Age in years (0 to 150).
     * @param phone 10-digit telephone number string.
     */
    public Patient(String name, String email, int age, String phone) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.active = true;
    }

    // Getters and Setters

    /**
     * Gets the unique primary key ID of the patient.
     * 
     * @return The patient database ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique primary key ID of the patient.
     * 
     * @param id The patient database ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the full legal name of the patient.
     * 
     * @return Full name string.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full legal name of the patient.
     * 
     * @param name Full name string (2 to 100 characters).
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique email address of the patient.
     * 
     * @return Patient email address string.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the unique email address of the patient.
     * 
     * @param email Unique email address string.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the patient's age in years.
     * 
     * @return Age integer (0 to 150).
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the patient's age in years.
     * 
     * @param age Age integer (0 to 150).
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the patient's 10-digit phone number.
     * 
     * @return Phone number string.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the patient's 10-digit phone number.
     * 
     * @param phone 10-digit phone number string.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Checks if the patient account is currently active.
     * 
     * @return True if patient is active, false if soft-deleted/deactivated.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active status flag for the patient.
     * 
     * @param active Active status boolean.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the optional medical history notes for the patient.
     * 
     * @return Medical history notes string, or null if none provided.
     */
    public String getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets the optional medical history notes for the patient.
     * 
     * @param medicalHistory Medical history notes string (max 1000 characters).
     */
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /**
     * Evaluates equality based on patient database ID and email address.
     * 
     * @param o Object to compare with this Patient.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(email, patient.email);
    }

    /**
     * Generates hash code based on patient ID and email.
     * 
     * @return Hash code integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    /**
     * Returns string representation of the Patient entity.
     * 
     * @return Formatted string containing key Patient fields.
     */
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                '}';
    }
}
