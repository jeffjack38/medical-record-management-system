package com.medicalrecords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medicalrecords.model.MedicalRecord;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    // find all medical records for a specific patient by patient ID
    List<MedicalRecord> findByPatientId(Long patientId);

    // find all medical records by record type
    List<MedicalRecord> findByRecordType(String recordType);

}
