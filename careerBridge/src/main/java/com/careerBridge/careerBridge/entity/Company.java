package com.careerBridge.careerBridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String email;
    private String phoneNumber;
    private String location;
    private String industry;
    private String website;

    public Company(){}

    public Company(String companyName, String email, String phoneNumber, String location, String industry, String website) {
        this.companyName=companyName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.location=location;
        this.industry=industry;
        this.website=website;
    }
    public Long getId(){
        return id;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName=companyName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
    this.email=email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location=location;
    }

    public String getIndustry(){
        return industry;
    }

    public void setIndustry(String industry){
        this.industry=industry;
    }

    public String getWebsite(){
        return website;
    }

    public void setWebsite(String website){
        this.website=website;
    }
}
