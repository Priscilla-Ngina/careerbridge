package com.careerBridge.careerBridge.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="internship")
public class Internship {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(length=1000)
    private String description;
    private String requirements;
    private String location;
    private String companyName;
    private String type;
    private int durationInMonths;
    private double stipend;
    private LocalDate applicationDeadline;
    private LocalDate startDate;
    private LocalDate endDate;

    public Internship(){}

    public Internship(String title, String description, String requirements, String location, String companyName, String type, int durationInMonths, double stipend, LocalDate applicationDeadline, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.companyName = companyName;
        this.type = type;
        this.durationInMonths = durationInMonths;
        this.stipend = stipend;
        this.applicationDeadline = applicationDeadline;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId(){
        return id;
    }

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
