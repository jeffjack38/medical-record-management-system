package com.medicalrecords.repository;

import com.medicalrecords.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Don't replace DataSource with an embedded one
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    @Rollback(false)
    public void testDatabaseConnectionAndCrudOperations() {
        // Create a new Patient entity
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setEmail("john.doe@example.com");
        patient.setAddress("123 Main St, City, Country");
        patient.setGender("Male");  // Set gender
        patient.setDateOfBirth(new Date(1990 - 1900, 1 - 1, 1));  // Set date of birth (Jan 1, 1990)
        patient.setPhoneNumber("123-456-7890");  // Set phone number



        // Save the patient entity using Spring Data JPA
        patientRepository.save(patient);

        // Retrieve the entity by email using the custom query method
        Patient savedPatient = patientRepository.findByEmail("john.doe@example.com");

        // Verify that the patient was saved and retrieved correctly
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getFirstName()).isEqualTo("John");
        assertThat(savedPatient.getLastName()).isEqualTo("Doe");
        assertThat(savedPatient.getGender()).isEqualTo("Male");
        assertThat(savedPatient.getAddress()).isEqualTo("123 Main St, City, Country");
        assertThat(savedPatient.getDateOfBirth()).isEqualTo(new Date(1990 - 1900, 1 - 1, 1));
        assertThat(savedPatient.getPhoneNumber()).isEqualTo("123-456-7890");

        // Check createdAt and updatedAt timestamps
        assertThat(savedPatient.getCreatedAt()).isNotNull();
        assertThat(savedPatient.getUpdatedAt()).isNotNull();

        // Update the patient entity
        savedPatient.setPhoneNumber("987-654-3210");
        patientRepository.save(savedPatient);

        // Assert that updatedAt was updated after saving again
        Patient updatedPatient = patientRepository.findByEmail("john.doe@example.com");
        assertThat(updatedPatient.getPhoneNumber()).isEqualTo("987-654-3210");
        assertThat(updatedPatient.getUpdatedAt()).isNotEqualTo(updatedPatient.getCreatedAt());
    }
}
