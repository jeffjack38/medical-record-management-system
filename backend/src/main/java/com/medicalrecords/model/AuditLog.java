package com.medicalrecords.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "action", nullable = false)
    private String action;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private MedicalRecord record;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "action_timestamp", nullable = false)
    private Date actionTimestamp;

    //no arg constructor

    public AuditLog() {
        super();
    }

    //constructor

    public AuditLog(Long id, User user, String action, MedicalRecord record, Patient patient, Date actionTimestamp) {
        this.id = id;
        this.user = user;
        this.action = action;
        this.record = record;
        this.patient = patient;
        this.actionTimestamp = actionTimestamp;
    }


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MedicalRecord getRecord() {
        return record;
    }

    public void setRecord(MedicalRecord record) {
        this.record = record;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(Date actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }

    //toString()

    @Override
    public String toString() {
        return "AuditLog{" +
                "user=" + user +
                ", action='" + action + '\'' +
                ", record=" + record +
                ", patient=" + patient +
                ", actionTimestamp=" + actionTimestamp +
                '}';
    }
}

