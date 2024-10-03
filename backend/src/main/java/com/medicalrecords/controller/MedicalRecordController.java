package com.medicalrecords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medicalrecords.model.MedicalRecord;
import com.medicalrecords.service.MedicalRecordService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    // Create a new Medical Record
    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createdRecord = medicalRecordService.createMedicalRecord(medicalRecord);
        return ResponseEntity.ok(createdRecord);
    }

    // Get all Medical Records
    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    // Get Medical Records by Patient ID
    @GetMapping("/patient/{patientId}")
    public List<MedicalRecord> getMedicalRecordsByPatientId(@PathVariable Long patientId) {
        return medicalRecordService.getMedicalRecordsByPatientId(patientId);
    }

    // Get a Medical Record by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        Optional<MedicalRecord> record = medicalRecordService.getMedicalRecordById(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Medical Record
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord updatedRecord = medicalRecordService.updateMedicalRecord(id, medicalRecord);
        if (updatedRecord != null) {
            return ResponseEntity.ok(updatedRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Medical Record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }
}

