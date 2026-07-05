package com.careerBridge.careerBridge.repository;


import com.careerBridge.careerBridge.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByStudentIdAndInternshipId(Long studentId, Long internshipId);

}
