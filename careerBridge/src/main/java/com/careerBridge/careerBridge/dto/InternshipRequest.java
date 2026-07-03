package com.careerBridge.careerBridge.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class InternshipRequest {

    @NotBlank(message="Title is required")
    private String title;

    @NotBlank(message="Description is required")
    private String description;

    @NotBlank(message="Requirements is required")
    private String requirements;

    @NotBlank(message="Location is required")
    private String location;

    @NotBlank(message="Company name is required")
    private String companyName;

    @NotBlank(message="Internship type is required")
    private String type;

    @Min(value=1, message="Duration must be at least 1 month")
    @NotNull(message="Duration is required")
    private Integer durationInMonths;

    @DecimalMin(value="0.0", message="Stipend cannot be negative")
    private Double stipend;

    @NotNull(message="Application date is required")
    private LocalDate applicationDeadline;

    @NotNull(message="Start date is required")
    private LocalDate startDate;

    @NotNull(message="End date is required")
    private LocalDate endDate;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getRequirements(){
        return requirements;
    }

    public void setRequirements(String requirements){
        this.requirements=requirements;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location=location;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName=companyName;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    public int getDurationInMonths(){
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths){
        this.durationInMonths=durationInMonths;
    }

    public double getStipend(){
        return stipend;
    }

    public void setStipend(double stipend){
        this.stipend=stipend;
    }

    public LocalDate getApplicationDeadline(){
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline){
        this.applicationDeadline= applicationDeadline;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate=startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate=endDate;
    }
}
