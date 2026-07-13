package com.careerBridge.careerBridge.controller;


import com.careerBridge.careerBridge.entity.Internship;
import com.careerBridge.careerBridge.service.InternshipService;
import com.careerBridge.careerBridge.dto.InternshipRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<Internship> getAllInternships(Pageable pageable) {
        return internshipService.getAllInternships(pageable);
    }

    @GetMapping("/search")
    public List<Internship> searchInternships(@RequestParam String keyword) {
        return internshipService.searchInternships(keyword);
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
