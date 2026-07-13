package com.careerBridge.careerBridge.repository;

import com.careerBridge.careerBridge.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

    List<Internship> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrRequirementsContainingIgnoreCaseOrLocationContainingIgnoreCaseOrTypeContainingIgnoreCase(
            String title,
            String description,
            String requirements,
            String location,
            String type
    );
}
