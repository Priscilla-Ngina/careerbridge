package com.careerBridge.careerBridge.controller;


import com.careerBridge.careerBridge.entity.Internship;
import com.careerBridge.careerBridge.service.InternshipService;
import com.careerBridge.careerBridge.dto.InternshipRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internships")
public class InternshipController {


    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @PostMapping
    public Internship addInternship(@Valid @RequestBody InternshipRequest request){


        return internshipService.saveInternship(request);
    }

    @GetMapping
    public List<Internship> getAllInternships(){
        return internshipService.getAllInternships();
    }

    @GetMapping("/{id}")
    public Internship getInternshipById(@PathVariable Long id){
        return internshipService.getInternshipById(id);
    }

    @PutMapping("/{id}")
    public Internship updateInternship(@PathVariable Long id, @RequestBody Internship internship){
        return internshipService.updateInternship(id, internship);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(@PathVariable Long id){
        internshipService.deleteInternshipById(id);
    }
}
