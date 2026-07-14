package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Student;
import com.careerBridge.careerBridge.entity.User;
import com.careerBridge.careerBridge.entity.Role;
import com.careerBridge.careerBridge.entity.Internship;
import com.careerBridge.careerBridge.dto.ApplicationCreateRequest;
import com.careerBridge.careerBridge.dto.ApplicationUpdateRequest;
import com.careerBridge.careerBridge.exception.ResourceNotFoundException;

import com.careerBridge.careerBridge.repository.StudentRepository;
import com.careerBridge.careerBridge.repository.InternshipRepository;

import com.careerBridge.careerBridge.entity.Application;
import com.careerBridge.careerBridge.repository.ApplicationRepository;
import com.careerBridge.careerBridge.security.SecurityService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final InternshipRepository internshipRepository;
    private final SecurityService securityService;
    private final FileStorageService fileStorageService;

    public ApplicationService(ApplicationRepository applicationRepository, StudentRepository studentRepository, InternshipRepository internshipRepository, SecurityService securityService, FileStorageService fileStorageService) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.internshipRepository = internshipRepository;
        this.securityService = securityService;
        this.fileStorageService = fileStorageService;
    }

    public Application saveApplication(ApplicationCreateRequest request) {

        User currentUser = securityService.getCurrentUser();

        if (currentUser.getRole() != Role.STUDENT) {
            throw new AccessDeniedException(
                    "Only students can apply for internships."
            );
        }

        Student student = studentRepository.findByUserId(currentUser.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student profile not found"));

        Internship internship = internshipRepository.findById(request.getInternshipId())
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found"));

        if(applicationRepository.existsByStudentIdAndInternshipId(
                student.getId(),
        internship.getId())){
            throw new IllegalArgumentException("You have already applied for this internship");
        }

        if(LocalDate.now().isAfter(internship.getApplicationDeadline())){

            throw new IllegalArgumentException("Application deadline has passed");

        }

        String cvFileName = fileStorageService.saveFile(request.getCv());

        Application application = new Application();

        application.setCreatedAt(LocalDateTime.now());
        application.setStatus("Pending");
        application.setCoverLetter(request.getCoverLetter());
        application.setCvFilePath(cvFileName);
        application.setStudent(student);
        application.setInternship(internship);


        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {

        User currentUser = securityService.getCurrentUser();

        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException(
                    "Only administrators can view all applications."
            );
        }

        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Application not found"));
    }

    public Application updateApplication(Long id, ApplicationUpdateRequest request) {
        Application existing = getApplicationById(id);

        verifyOwnership(existing);

        if(!existing.getStatus().equalsIgnoreCase("Pending")){
            throw new IllegalArgumentException("You cannot update an application that has already been reviewed");
        }

        if(LocalDate.now().isAfter(existing.getInternship().getApplicationDeadline())){
            throw new IllegalArgumentException("Application deadline has passed");
        }




        existing.setCoverLetter(request.getCoverLetter());
        if (request.getCv() != null && !request.getCv().isEmpty()) {

            fileStorageService.deleteFile(existing.getCvFilePath());

            String newCvFileName = fileStorageService.saveFile(request.getCv());

            existing.setCvFilePath(newCvFileName);
        }

        return applicationRepository.save(existing);
    }

    public Application updateStatus(Long id, String status) {
        Application application = getApplicationById(id);

        verifyCompanyOwnership(application);

        if(!application.getStatus().equalsIgnoreCase("Pending")){
            throw new IllegalArgumentException("This application has already been reviewed");
        }
        if(!status.equalsIgnoreCase("Accepted") &&
         !status.equalsIgnoreCase("Rejected")){
            throw new IllegalArgumentException("Status must be Accepted or Rejected");
        }

        application.setStatus(status);

        return applicationRepository.save(application);
    }

    public void deleteApplicationById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Application not found"));
        verifyOwnership(application);
        applicationRepository.delete(application);
    }

    public List<Application> getMyApplications() {

        User currentUser = securityService.getCurrentUser();

        if (currentUser.getRole() != Role.STUDENT) {
            throw new AccessDeniedException(
                    "Only students can view their applications."
            );
        }

        return applicationRepository.findByStudentUserId(currentUser.getId());
    }

    public List<Application> getApplicationsForMyInternships() {

        User currentUser = securityService.getCurrentUser();

        if (currentUser.getRole() != Role.COMPANY) {
            throw new AccessDeniedException(
                    "Only companies can view applications for their internships."
            );
        }

        return applicationRepository.findByInternshipCompanyUserId(currentUser.getId());
    }


    private void verifyOwnership(Application application) {

        User currentUser = securityService.getCurrentUser();

        if (currentUser.getRole() == Role.ADMIN) {
            return;
        }

        if (!currentUser.getId().equals(application.getStudent().getUser().getId())) {
            throw new AccessDeniedException(
                    "You do not have permission to modify this application."
            );
        }
    }

    private void verifyCompanyOwnership(Application application) {

        User currentUser = securityService.getCurrentUser();

        if (currentUser.getRole() == Role.ADMIN) {
            return;
        }

        if (currentUser.getRole() != Role.COMPANY) {
            throw new AccessDeniedException(
                    "Only the company that posted this internship can review applications."
            );
        }

        if (!currentUser.getId().equals(application.getInternship().getCompany().getUser().getId())) {
            throw new AccessDeniedException(
                    "You do not have permission to review applications for this internship."
            );
        }
    }

    public Path downloadCv(Long applicationId) {

        Application application = getApplicationById(applicationId);

        verifyCompanyOwnership(application);

        return fileStorageService.loadFile(application.getCvFilePath());
    }

}
