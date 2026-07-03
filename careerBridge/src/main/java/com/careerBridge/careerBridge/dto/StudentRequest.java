package com.careerBridge.careerBridge.dto;

import jakarta.validation.constraints.*;

public class StudentRequest {

    @NotBlank(message="Name is required")
    private String name;

    @Email(message="Invalid email format")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message="Admission number is required")
    private String admissionNumber;

    @NotBlank(message="Course is required")
    private String course;

  @Min(value=1, message="Year of study must be at least 1")
  @Max(value=6, message="Year of study cannot exceed 6")
    private int yearOfStudy;

    @NotBlank(message="Phone number is required")
    @Pattern(
            regexp = "^(07\\d{8}|01\\d{8}|\\+2547\\d{8}|\\+2541\\d{8})$",
            message="Phone number must be a valid kenyan number"
    )
    private String phoneNumber;

    @NotNull(message="User id is required")
    private Long userId;

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

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){

        this.userId= userId;
    }
}
