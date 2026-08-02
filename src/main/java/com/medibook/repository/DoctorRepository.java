package com.medibook.repository;

import com.medibook.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA Repository for managing {@link Doctor} entity persistence.
 * 
 * @author MediBook Team
 * @version 1.0
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Finds all doctors matching a given specialization.
     * 
     * @param specialization Medical specialization filter string.
     * @return List of matching Doctor entities, empty list if none found.
     */
    List<Doctor> findBySpecialization(String specialization);

    /**
     * Finds all available doctors.
     * 
     * @param available Available status flag.
     * @return List of available Doctor entities.
     */
    List<Doctor> findByAvailable(boolean available);
}
