package com.careerBridge.careerBridge.repository;

import com.careerBridge.careerBridge.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByUserId(Long userId);
    Optional<Student> findByUserId(Long userId);

}
