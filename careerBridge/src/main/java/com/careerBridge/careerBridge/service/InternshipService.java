package com.careerBridge.careerBridge.service;


import com.careerBridge.careerBridge.entity.Internship;
import com.careerBridge.careerBridge.repository.CompanyRepository;
import com.careerBridge.careerBridge.repository.InternshipRepository;
import com.careerBridge.careerBridge.dto.InternshipRequest;
import com.careerBridge.careerBridge.exception.ResourceNotFoundException;
import com.careerBridge.careerBridge.security.SecurityService;
import org.springframework.stereotype.Service;
import com.careerBridge.careerBridge.entity.Company;
import org.springframework.security.access.AccessDeniedException;
import com.careerBridge.careerBridge.entity.User;
import com.careerBridge.careerBridge.entity.Role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;
    private final SecurityService securityService;

    public InternshipService(InternshipRepository internshipRepository, CompanyRepository companyRepository, SecurityService securityService) {
        this.internshipRepository = internshipRepository;
        this.companyRepository = companyRepository;
        this.securityService = securityService;
    }

    public Internship saveInternship(InternshipRequest request) {

        User currentUser = securityService.getCurrentUser();

        Company company = companyRepository.findByUserId(currentUser.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company profile not found"));

        Internship internship = new Internship();

        internship.setCompany(company);

        internship.setCreatedAt(LocalDateTime.now());
        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setRequirements(request.getRequirements());
        internship.setLocation(request.getLocation());
        internship.setType(request.getType());
        internship.setDurationInMonths(request.getDurationInMonths());
        internship.setStipend(request.getStipend());
        internship.setApplicationDeadline(request.getApplicationDeadline());
        internship.setStartDate(request.getStartDate());
        internship.setEndDate(request.getEndDate());

        return internshipRepository.save(internship);
    }

    public Page<Internship> getAllInternships(Pageable pageable) {
        return internshipRepository.findAll(pageable);
    }

    public List<Internship> searchInternships(String keyword) {
        return internshipRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrRequirementsContainingIgnoreCaseOrLocationContainingIgnoreCaseOrTypeContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword,
                        keyword,
                        keyword
                );
    }

    public Internship getInternshipById(Long id) {
        return internshipRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Internship not found"));
    }

    public Internship updateInternship(Long id, Internship internship) {
        Internship existing = getInternshipById(id);

        verifyOwnership(existing);

        existing.setTitle(internship.getTitle());
        existing.setDescription(internship.getDescription());
        existing.setRequirements(internship.getRequirements());
        existing.setLocation(internship.getLocation());
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
        verifyOwnership(internship);
        internshipRepository.delete(internship);
    }

    private void verifyOwnership(Internship internship) {
        User currentUser = securityService.getCurrentUser();

        if(currentUser.getRole()==Role.ADMIN){
            return;
        }

        if(!currentUser.getId().equals(internship.getCompany().getUser().getId())) {
            throw new AccessDeniedException("You do not have permission to modify this Internship");
        }

    }

}
