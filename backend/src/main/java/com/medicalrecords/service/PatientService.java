package com.medicalrecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medicalrecords.model.Patient;
import com.medicalrecords.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Create a new Patient
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Read - Get all Patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Read - Get a Patient by ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Update a Patient
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Optional<Patient> existingPatient = patientRepository.findById(id);

        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            patient.setFirstName(updatedPatient.getFirstName());
            patient.setLastName(updatedPatient.getLastName());
            patient.setDateOfBirth(updatedPatient.getDateOfBirth());
            patient.setAddress(updatedPatient.getAddress());
            patient.setGender(updatedPatient.getGender());
            patient.setPhoneNumber(updatedPatient.getPhoneNumber());
            patient.setEmail(updatedPatient.getEmail());
            patient.setUpdatedAt(new java.util.Date()); // Manually update the updatedAt field
            return patientRepository.save(patient);  // Save the updated patient
        }
        return null; // Or throw an exception if the patient is not found
    }

    // Delete a Patient
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}

