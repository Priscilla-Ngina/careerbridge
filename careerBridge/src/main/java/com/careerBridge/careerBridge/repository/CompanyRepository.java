package com.careerBridge.careerBridge.repository;

import com.careerBridge.careerBridge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
