package com.careerBridge.careerBridge.repository;

import com.careerBridge.careerBridge.entity.Company;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByUserId(Long userId);

    Optional<Company> findByUserId(Long userId);
}
