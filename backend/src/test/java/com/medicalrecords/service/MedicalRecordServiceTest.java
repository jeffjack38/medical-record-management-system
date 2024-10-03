package com.medicalrecords.service;

import com.medicalrecords.model.MedicalRecord;
import com.medicalrecords.model.Patient;
import com.medicalrecords.repository.MedicalRecordRepository;
import com.medicalrecords.service.MedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testCreateMedicalRecord() {
        // Arrange
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "123 Main St", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        MedicalRecord record = new MedicalRecord(1L, patient, "Type1", "Description", new Date(), null, new Date());
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(record);

        // Act
        MedicalRecord createdRecord = medicalRecordService.createMedicalRecord(record);

        // Assert
        assertNotNull(createdRecord);
        assertEquals("Type1", createdRecord.getRecordType());
        verify(medicalRecordRepository, times(1)).save(record);
    }

    @Test
    public void testGetMedicalRecordById() {
        // Arrange
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "123 Main St", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        MedicalRecord record = new MedicalRecord(1L, patient, "Type1", "Description", new Date(), null, new Date());
        when(medicalRecordRepository.findById(1L)).thenReturn(Optional.of(record));

        // Act
        Optional<MedicalRecord> foundRecord = medicalRecordService.getMedicalRecordById(1L);

        // Assert
        assertTrue(foundRecord.isPresent());
        assertEquals("Type1", foundRecord.get().getRecordType());
    }

    @Test
    public void testGetAllMedicalRecords() {
        // Arrange
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "123 Main St", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        MedicalRecord record1 = new MedicalRecord(1L, patient, "Type1", "Description1", new Date(), null, new Date());
        MedicalRecord record2 = new MedicalRecord(2L, patient, "Type2", "Description2", new Date(), null, new Date());
        when(medicalRecordRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        // Act
        List<MedicalRecord> allRecords = medicalRecordService.getAllMedicalRecords();

        // Assert
        assertEquals(2, allRecords.size());
        verify(medicalRecordRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateMedicalRecord() {
        // Arrange
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "123 Main St", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        MedicalRecord existingRecord = new MedicalRecord(1L, patient, "Type1", "Description1", new Date(), null, new Date());
        MedicalRecord updatedRecord = new MedicalRecord(1L, patient, "Type1", "Updated Description", new Date(), null, new Date());
        when(medicalRecordRepository.findById(1L)).thenReturn(Optional.of(existingRecord));
        when(medicalRecordRepository.save(existingRecord)).thenReturn(existingRecord);

        // Act
        MedicalRecord result = medicalRecordService.updateMedicalRecord(1L, updatedRecord);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Description", result.getDescription());
        verify(medicalRecordRepository, times(1)).save(existingRecord);
    }

    @Test
    public void testDeleteMedicalRecord() {
        // Act
        medicalRecordService.deleteMedicalRecord(1L);

        // Assert
        verify(medicalRecordRepository, times(1)).deleteById(1L);
    }
}


