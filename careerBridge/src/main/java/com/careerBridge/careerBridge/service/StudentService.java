package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Student;
import com.careerBridge.careerBridge.entity.User;
import com.careerBridge.careerBridge.entity.Role;
import com.careerBridge.careerBridge.repository.StudentRepository;
import com.careerBridge.careerBridge.repository.UserRepository;
import com.careerBridge.careerBridge.dto.StudentRequest;
import org.springframework.stereotype.Service;
import com.careerBridge.careerBridge.exception.ResourceNotFoundException;
import com.careerBridge.careerBridge.security.SecurityService;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.time.LocalDateTime;



@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final  UserRepository userRepository;
    private final SecurityService securityService;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository, SecurityService securityService) {
        this.studentRepository=studentRepository;
        this.userRepository=userRepository;
        this.securityService=securityService;
    }

    public Student saveStudent(StudentRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(user.getRole()!=Role.STUDENT){
            throw new IllegalArgumentException("Only STUDENT users can create student profiles");
        }

        if(studentRepository.existsByUserId(user.getId())){
            throw new IllegalArgumentException("This user already has a student profile");
        }

        if (!securityService.getCurrentUserId().equals(request.getUserId())) {
            throw new AccessDeniedException("You are not authorized to create a student profile for another user.");
        }

        Student student = new Student();

        student.setUser(user);
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setAdmissionNumber(request.getAdmissionNumber());
        student.setCourse(request.getCourse());
        student.setYearOfStudy(request.getYearOfStudy());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCreatedAt(LocalDateTime.now());

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Student not found"));
    }

    public Student updateStudent(Long id, Student newStudent) {
        Student existing = studentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Student not found"));

        verifyOwnership(existing);

        existing.setName(newStudent.getName());
        existing.setCourse(newStudent.getCourse());
        existing.setYearOfStudy(newStudent.getYearOfStudy());
        existing.setEmail(newStudent.getEmail());
        existing.setPhoneNumber(newStudent.getPhoneNumber());
        existing.setAdmissionNumber(newStudent.getAdmissionNumber());

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Student not found"));
        verifyOwnership(student);

        studentRepository.delete(student);
    }

    private void verifyOwnership(Student student) {

        User currentUser = securityService.getCurrentUser();

        if(currentUser.getRole()==Role.ADMIN){
            return;
        }

        if(!currentUser.getId().equals(student.getUser().getId())){
            throw new AccessDeniedException("You do not have permission to access this student profile."
            );
        }

    }

}
