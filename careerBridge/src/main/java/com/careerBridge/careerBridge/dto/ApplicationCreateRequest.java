package com.careerBridge.careerBridge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class ApplicationCreateRequest {


  @NotBlank(message="Cover letter is required")
    private String coverLetter;

    @NotNull(message = "CV is required")
    private MultipartFile cv;


  @NotNull(message="Internship id is required")
    private Long internshipId;





    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

    public MultipartFile getCv() {
        return cv;
    }

    public void setCv(MultipartFile cv) {
        this.cv = cv;
    }

    public Long getInternshipId() { return internshipId; }
    public void setInternshipId(Long internshipId) { this.internshipId = internshipId; }
}

