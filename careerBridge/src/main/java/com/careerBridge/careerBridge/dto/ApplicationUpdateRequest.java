package com.careerBridge.careerBridge.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class ApplicationUpdateRequest {

    @NotBlank(message = "Cover letter is required")
    private String coverLetter;

    private MultipartFile cv;

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public MultipartFile getCv() {
        return cv;
    }

    public void setCv(MultipartFile cv) {
        this.cv = cv;
    }
}
