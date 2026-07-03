package com.careerBridge.careerBridge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    private String admissionNumber;

    private String course;

    private int yearOfStudy;

    private String phoneNumber;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name="User_id")
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Application> applications;

    public Student(){}

    public Student(String name, String email, String admissionNumber, String course, int yearOfStudy, String phoneNumber) {
        this.name=name;
        this.email=email;
        this.admissionNumber=admissionNumber;
        this.course=course;
        this.yearOfStudy=yearOfStudy;
        this.phoneNumber=phoneNumber;
    }

    public long getId(){
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

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getAdmissionNumber(){
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber){
        this.admissionNumber=admissionNumber;
    }

    public String getCourse(){
        return course;
    }

    public void setCourse(String course){
        this.course=course;
    }

    public int getYearOfStudy(){
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy){
        this.yearOfStudy=yearOfStudy;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
    this.phoneNumber=phoneNumber;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

}
