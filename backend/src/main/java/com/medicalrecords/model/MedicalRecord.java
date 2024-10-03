package com.medicalrecords.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "record_type", nullable = false)
    private String recordType;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_recorded", nullable = false)
    private Date dateRecorded;

    @ManyToOne
    @JoinColumn(name = "created_by_user", nullable = false)
    private User createdByUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    //no arg constructor

    public MedicalRecord() {
        super();
    }

    //constructor

    public MedicalRecord(Long id, Patient patient, String recordType, String description, Date dateRecorded, User createdByUser, Date updatedAt) {
        this.id = id;
        this.patient = patient;
        this.recordType = recordType;
        this.description = description;
        this.dateRecorded = dateRecorded;
        this.createdByUser = createdByUser;
        this.updatedAt = updatedAt;
    }


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(Date dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    //toString()

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "patient=" + patient +
                ", recordType='" + recordType + '\'' +
                ", description='" + description + '\'' +
                ", dateRecorded=" + dateRecorded +
                ", createdByUser=" + createdByUser +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
