package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Student;
import com.careerBridge.careerBridge.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository=studentRepository;
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student newStudent) {
        Student existing = getStudentById(id);

        existing.setName(newStudent.getName());
        existing.setCourse(newStudent.getCourse());
        existing.setYearOfStudy(newStudent.getYearOfStudy());
        existing.setEmail(newStudent.getEmail());
        existing.setPhoneNumber(newStudent.getPhoneNumber());
        existing.setAdmissionNumber(newStudent.getAdmissionNumber());

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
