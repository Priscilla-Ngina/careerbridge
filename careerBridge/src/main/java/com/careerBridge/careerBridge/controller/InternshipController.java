package com.careerBridge.careerBridge.controller;


import com.careerBridge.careerBridge.entity.Internship;
import com.careerBridge.careerBridge.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internships")
public class InternshipController {

    @Autowired
    private InternshipService internshipService;

    @PostMapping
    public Internship addInternship(@RequestBody Internship internship){
        return internshipService.saveInternship(internship);
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
