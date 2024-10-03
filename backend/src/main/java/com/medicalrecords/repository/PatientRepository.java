package com.medicalrecords.repository;

import com.medicalrecords.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);  // Custom query method to find by email
}


