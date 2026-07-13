package com.careerBridge.careerBridge.controller;

import com.careerBridge.careerBridge.entity.Application;

import com.careerBridge.careerBridge.dto.ApplicationCreateRequest;
import com.careerBridge.careerBridge.dto.ApplicationUpdateRequest;

import com.careerBridge.careerBridge.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Application addApplication(@Valid @ModelAttribute ApplicationCreateRequest request) {
        return applicationService.saveApplication(request);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }


    @GetMapping("/my")
    public List<Application> getMyApplications() {
        return applicationService.getMyApplications();
    }

    @GetMapping("/company")
    public List<Application> getApplicationsForMyInternships() {
        return applicationService.getApplicationsForMyInternships();
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Application updateApplication(
            @PathVariable Long id,
            @Valid @ModelAttribute ApplicationUpdateRequest request) {

        return applicationService.updateApplication(id, request);
    }

    @PatchMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @RequestParam String status) {
        return applicationService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteApplicationById(@PathVariable Long id) {
        applicationService.deleteApplicationById(id);
    }
}
