package com.medicalrecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medicalrecords.model.MedicalRecord;
import com.medicalrecords.repository.MedicalRecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    // Create a new Medical Record
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    // Read - Get all Medical Records
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    // Read - Get Medical Records by Patient ID
    public List<MedicalRecord> getMedicalRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    // Read - Get a Medical Record by ID
    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    // Update a Medical Record
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord updatedRecord) {
        Optional<MedicalRecord> existingRecord = medicalRecordRepository.findById(id);

        if (existingRecord.isPresent()) {
            MedicalRecord medicalRecord = existingRecord.get();
            medicalRecord.setRecordType(updatedRecord.getRecordType());
            medicalRecord.setDescription(updatedRecord.getDescription());
            medicalRecord.setDateRecorded(updatedRecord.getDateRecorded());
            medicalRecord.setCreatedByUser(updatedRecord.getCreatedByUser());
            medicalRecord.setUpdatedAt(new java.util.Date()); // Manually update the updatedAt field
            return medicalRecordRepository.save(medicalRecord); // Save the updated record
        }
        return null; // Or throw an exception if the record is not found
    }

    // Delete a Medical Record
    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }
}

