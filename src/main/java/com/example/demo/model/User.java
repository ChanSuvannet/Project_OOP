package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    
    @Id // This annotation specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    private Long id;
    
    @Column(name = "UserName")
    private String fullName;
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Column(name = "UserID")
    private String userID;
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    @Column(name = "UserEmail")
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "UserPs")
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}