package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Student;
import com.careerBridge.careerBridge.entity.User;
import com.careerBridge.careerBridge.entity.Role;
import com.careerBridge.careerBridge.repository.StudentRepository;
import com.careerBridge.careerBridge.repository.UserRepository;
import com.careerBridge.careerBridge.dto.StudentRequest;
import org.springframework.stereotype.Service;
import com.careerBridge.careerBridge.exception.ResourceNotFoundException;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private UserRepository userRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository=studentRepository;
        this.userRepository=userRepository;
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
        studentRepository.delete(student);
    }
}
