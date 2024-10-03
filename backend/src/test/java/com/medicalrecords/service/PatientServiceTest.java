package com.medicalrecords.service;

import com.medicalrecords.model.Patient;
import com.medicalrecords.repository.PatientRepository;
import com.medicalrecords.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testCreatePatient() {
        // Arrange
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "123 Main St", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Act
        Patient createdPatient = patientService.createPatient(patient);

        // Assert
        assertNotNull(createdPatient);
        assertEquals("John", createdPatient.getFirstName());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testGetPatientById() {
        // Arrange
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "123 Main St", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // Act
        Optional<Patient> foundPatient = patientService.getPatientById(1L);

        // Assert
        assertTrue(foundPatient.isPresent());
        assertEquals("John", foundPatient.get().getFirstName());
    }

    @Test
    public void testGetAllPatients() {
        // Arrange
        Patient patient1 = new Patient(1L, "John", "Doe", new Date(), "123 Main St", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        Patient patient2 = new Patient(2L, "Jane", "Smith", new Date(), "456 Elm St", "Female", "9876543210", "jane.smith@example.com", new Date(), new Date());
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        // Act
        var allPatients = patientService.getAllPatients();

        // Assert
        assertEquals(2, allPatients.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testUpdatePatient() {
        // Arrange
        Patient existingPatient = new Patient(1L, "John", "Doe", new Date(), "123 Main St", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        Patient updatedPatient = new Patient(1L, "John", "Doe", new Date(), "456 New Address", "Male", "1234567890", "john.doe@example.com", new Date(), new Date());
        when(patientRepository.findById(1L)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(existingPatient)).thenReturn(existingPatient);

        // Act
        Patient result = patientService.updatePatient(1L, updatedPatient);

        // Assert
        assertNotNull(result);
        assertEquals("456 New Address", result.getAddress());
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    public void testDeletePatient() {
        // Act
        patientService.deletePatient(1L);

        // Assert
        verify(patientRepository, times(1)).deleteById(1L);
    }
}

