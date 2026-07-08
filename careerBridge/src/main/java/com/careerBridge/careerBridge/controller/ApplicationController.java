package com.careerBridge.careerBridge.controller;

import com.careerBridge.careerBridge.entity.Application;

import com.careerBridge.careerBridge.dto.ApplicationRequest;

import com.careerBridge.careerBridge.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public Application addApplication(@Valid @RequestBody ApplicationRequest request) {
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

    @PutMapping("/{id}")
    public Application updateApplication(@PathVariable Long id, @RequestBody ApplicationRequest request) {
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
