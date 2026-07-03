package com.careerBridge.careerBridge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CompanyRequest {

    @NotBlank(message="Company name is required")
    private String companyName;

    @Email(message="Invalid email format")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message="Phone number is required")
    @Pattern(
            regexp = "^(07\\d{8}|01\\d{8}|\\+2547\\d{8}|\\+2541\\d{8})$",
            message="Phone number must be a valid kenyan number"
    )
    private String phoneNumber;

    @NotBlank(message="Location is required")
    private String location;

    @NotBlank(message="Industry is required")
    private String industry;

    @NotBlank(message="website is required")
    private String website;


    @NotNull(message="Student id is required")
    private Long userId;


    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getIndustry(){
        return industry;
    }

    public void setIndustry(String industry){
        this.industry = industry;
    }

    public String getWebsite(){
        return website;
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }
}
