package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Internship;
import com.careerBridge.careerBridge.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InternshipService {
    @Autowired
    private InternshipRepository internshipRepository;

    public Internship saveInternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    public Internship getInternshipById(Long id) {
        return internshipRepository.findById(id).orElse(null);
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
        internshipRepository.deleteById(id);
    }
}
