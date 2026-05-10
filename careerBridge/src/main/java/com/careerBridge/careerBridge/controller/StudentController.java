package com.careerBridge.careerBridge.controller;

import com.careerBridge.careerBridge.entity.Student;
import com.careerBridge.careerBridge.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private  StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllAStudents(){
        return studentService.getAllStudents();
    }
@GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
}

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

@DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id){
        studentService.deleteStudent(id);
}

}
