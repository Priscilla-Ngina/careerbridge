package com.careerBridge.careerBridge.repository;

import com.careerBridge.careerBridge.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
