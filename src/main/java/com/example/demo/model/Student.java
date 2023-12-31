package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;//PK
    
    @Column(name = "sringid")
    private String nameid;
   
    public String getNameid() {
        return nameid;
    }
    public void setNameid(String nameid) {
        this.nameid = nameid;
    }
    @Column(name = "Stu_fName")
    private String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "Stu_lName")
    private String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "Stu_gender")
    private String gender;
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column(name = "stu_group")
    private String group;
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    @Column(name = "Stu_department")
    private String department;
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    @Column(name = "Stu_academyYear")
    private String academyYear;
    public String getAcademyYear() {
        return academyYear;
    }
    public void setAcademyYear(String academyYear) {
        this.academyYear = academyYear;
    }
    @Column(name = "Stu_email")
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "Stu_phone")
    private String phone;
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "Stu_general")
    private String general;
    public String getGeneral() {
        return general;
    }
    public void setGeneral(String general) {
        this.general = general;
    }
}
