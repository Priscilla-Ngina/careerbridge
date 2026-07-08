package com.careerBridge.careerBridge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ApplicationRequest {


  @NotBlank(message="Cover letter is required")
    private String coverLetter;

  @NotBlank(message="Cv file path is required")
    private String cvFilePath;


  @NotNull(message="Internship id is required")
    private Long internshipId;





    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

    public String getCvFilePath() { return cvFilePath; }
    public void setCvFilePath(String cvFilePath) { this.cvFilePath = cvFilePath; }


    public Long getInternshipId() { return internshipId; }
    public void setInternshipId(Long internshipId) { this.internshipId = internshipId; }
}

