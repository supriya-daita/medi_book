package com.medibook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

/**
 * Represents a registered doctor with a specialization and fee structure in MediBook.
 * 
 * <p>Doctors must have a consultation fee between ₹100 and ₹10,000 (INR).
 * Daily appointment capacity must be between 1 and 50 patients.</p>
 * 
 * Example usage:
 * <pre>
 *     Doctor doctor = new Doctor("Dr. House", "Diagnostics", 1500.0, 10);
 * </pre>
 * 
 * @author MediBook Team
 * @version 1.0
 */
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Full legal name of the doctor. Must not be blank. */
    @NotBlank(message = "Doctor name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    /** Medical specialization (e.g., Cardiology, Surgery, Pediatrics). */
    @NotBlank(message = "Specialization is required")
    private String specialization;

    /**
     * Consultation fee in INR.
     * Minimum: ₹100 (minimum viable consultation)
     * Maximum: ₹10,000 (premium specialist fee)
     */
    @Min(value = 100, message = "Consultation fee must be at least ₹100")
    @Max(value = 10000, message = "Consultation fee cannot exceed ₹10,000")
    private double consultationFee;

    /** Maximum number of appointments per day this doctor can handle (1 to 50). */
    @Min(value = 1, message = "Max appointments per day must be at least 1")
    @Max(value = 50, message = "Max appointments per day cannot exceed 50")
    private int maxAppointmentsPerDay;

    /** Availability flag indicating whether doctor is accepting new appointments. */
    private boolean available = true;

    /** Default no-argument constructor required by JPA. */
    public Doctor() {
    }

    /**
     * Parameterized constructor for creating a Doctor instance.
     * 
     * @param name Doctor's full name.
     * @param specialization Medical specialization.
     * @param consultationFee Fee per consultation (₹100 - ₹10,000).
     * @param maxAppointmentsPerDay Daily appointment limit (1 - 50).
     */
    public Doctor(String name, String specialization, double consultationFee, int maxAppointmentsPerDay) {
        this.name = name;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.maxAppointmentsPerDay = maxAppointmentsPerDay;
        this.available = true;
    }

    // Getters and Setters

    /**
     * Gets the primary key ID of the doctor.
     * 
     * @return Doctor database ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key ID of the doctor.
     * 
     * @param id Doctor database ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the full legal name of the doctor.
     * 
     * @return Doctor name string.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full legal name of the doctor.
     * 
     * @param name Doctor name string.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the medical specialization of the doctor.
     * 
     * @return Specialization string.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the medical specialization of the doctor.
     * 
     * @param specialization Specialization string.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets the consultation fee in INR.
     * 
     * @return Consultation fee double.
     */
    public double getConsultationFee() {
        return consultationFee;
    }

    /**
     * Sets the consultation fee in INR.
     * 
     * @param consultationFee Consultation fee double (₹100 to ₹10,000).
     */
    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    /**
     * Gets the maximum number of daily appointments.
     * 
     * @return Daily appointment cap integer.
     */
    public int getMaxAppointmentsPerDay() {
        return maxAppointmentsPerDay;
    }

    /**
     * Sets the maximum number of daily appointments.
     * 
     * @param maxAppointmentsPerDay Daily appointment cap integer (1 to 50).
     */
    public void setMaxAppointmentsPerDay(int maxAppointmentsPerDay) {
        this.maxAppointmentsPerDay = maxAppointmentsPerDay;
    }

    /**
     * Checks if the doctor is currently accepting new appointments.
     * 
     * @return True if available, false otherwise.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability flag for the doctor.
     * 
     * @param available Availability flag boolean.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Compares equality based on ID and doctor name.
     * 
     * @param o Object to compare.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(name, doctor.name);
    }

    /**
     * Computes hash code for Doctor instance.
     * 
     * @return Hash code integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Formats string representation of Doctor entity.
     * 
     * @return Formatted string representation.
     */
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", consultationFee=" + consultationFee +
                ", available=" + available +
                '}';
    }
}
