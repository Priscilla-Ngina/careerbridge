package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Company;
import com.careerBridge.careerBridge.entity.Internship;
import com.careerBridge.careerBridge.repository.InternshipRepository;
import com.careerBridge.careerBridge.dto.InternshipRequest;
import com.careerBridge.careerBridge.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class InternshipService {
    @Autowired
    private InternshipRepository internshipRepository;

    public Internship saveInternship(InternshipRequest request) {

        Internship internship = new Internship();

        internship.setCreatedAt(LocalDateTime.now());
        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setRequirements(request.getRequirements());
        internship.setLocation(request.getLocation());
        internship.setCompanyName(request.getCompanyName());
        internship.setType(request.getType());
        internship.setDurationInMonths(request.getDurationInMonths());
        internship.setStipend(request.getStipend());
        internship.setApplicationDeadline(request.getApplicationDeadline());
        internship.setStartDate(request.getStartDate());
        internship.setEndDate(request.getEndDate());

        return internshipRepository.save(internship);
    }

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    public Internship getInternshipById(Long id) {
        return internshipRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Internship not found"));
    }

    public Internship updateInternship(Long id, Internship internship) {
        Internship existing = getInternshipById(id);

        existing.setTitle(internship.getTitle());
        existing.setDescription(internship.getDescription());
        existing.setRequirements(internship.getRequirements());
        existing.setLocation(internship.getLocation());
        existing.setCompanyName(internship.getCompanyName());
        existing.setType(internship.getType());
        existing.setDurationInMonths(internship.getDurationInMonths());
        existing.setStipend(internship.getStipend());
        existing.setApplicationDeadline(internship.getApplicationDeadline());
        existing.setStartDate(internship.getStartDate());
        existing.setEndDate(internship.getEndDate());

        return internshipRepository.save(existing);
    }

    public void deleteInternshipById(Long id) {
        Internship internship=internshipRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Internship not found"));
        internshipRepository.delete(internship);
    }
}
