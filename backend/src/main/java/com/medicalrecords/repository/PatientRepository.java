package com.medicalrecords.repository;

import com.medicalrecords.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);  // find by email

    // find patients by first and last name
    Patient findByFirstNameAndLastName(String firstName, String lastName);

}


