package com.medicalrecords.dto;

public class RegisterRequest {
    private String username;
    private String password;

    // Default constructor
    public RegisterRequest() {}

    // Constructor with fields
    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

