package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Student;
import com.careerBridge.careerBridge.entity.Internship;
import com.careerBridge.careerBridge.dto.ApplicationRequest;
import com.careerBridge.careerBridge.exception.ResourceNotFoundException;

import com.careerBridge.careerBridge.repository.StudentRepository;
import com.careerBridge.careerBridge.repository.InternshipRepository;

import com.careerBridge.careerBridge.entity.Application;
import com.careerBridge.careerBridge.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    public Application saveApplication(ApplicationRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

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

        Application application = new Application();
        application.setCreatedAt(LocalDateTime.now());
        application.setStatus("Pending");
        application.setCoverLetter(request.getCoverLetter());
        application.setCvFilePath(request.getCvFilePath());
        application.setStudent(student);
        application.setInternship(internship);


        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Application not found"));
    }

    public Application updateApplication(Long id, ApplicationRequest request) {
        Application existing = getApplicationById(id);

        if(!existing.getStatus().equalsIgnoreCase("Pending")){
            throw new IllegalArgumentException("You cannot update an application that has already been reviewed");
        }

        if(LocalDate.now().isAfter(existing.getInternship().getApplicationDeadline())){
            throw new IllegalArgumentException("Application deadline has passed");
        }




        existing.setCoverLetter(request.getCoverLetter());
        existing.setCvFilePath(request.getCvFilePath());


        return applicationRepository.save(existing);
    }

    public Application updateStatus(Long id, String status) {
        Application application = getApplicationById(id);

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
        applicationRepository.delete(application);
    }
}
