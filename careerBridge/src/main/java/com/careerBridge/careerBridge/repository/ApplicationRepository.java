package com.careerBridge.careerBridge.repository;


import com.careerBridge.careerBridge.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByStudentIdAndInternshipId(Long studentId, Long internshipId);
    List<Application> findByStudentUserId(Long userId);

    List<Application> findByInternshipCompanyUserId(Long userId);

}
