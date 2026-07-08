package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Company;
import com.careerBridge.careerBridge.entity.Role;
import com.careerBridge.careerBridge.entity.User;
import com.careerBridge.careerBridge.repository.CompanyRepository;
import com.careerBridge.careerBridge.dto.CompanyRequest;
import com.careerBridge.careerBridge.exception.ResourceNotFoundException;
import com.careerBridge.careerBridge.repository.UserRepository;
import com.careerBridge.careerBridge.security.SecurityService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final SecurityService securityService;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository, SecurityService securityService) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

public Company saveCompany(CompanyRequest request){

    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    if(user.getRole() !=Role.COMPANY){
        throw new IllegalArgumentException("Only COMPANY users can create company profiles");
    }

    if(companyRepository.existsByUserId(user.getId())){
        throw new IllegalArgumentException("This user already has a company profile");
    }

    Company company = new Company();

    company.setUser(user);
company.setCompanyName(request.getCompanyName());
    company.setEmail(request.getEmail());
    company.setPhoneNumber(request.getPhoneNumber());
    company.setLocation(request.getLocation());
    company.setIndustry(request.getIndustry());
    company.setWebsite(request.getWebsite());
    company.setCreatedAt(LocalDateTime.now());


    return companyRepository.save(company);
}

public List<Company> getAllCompanies(){
    return companyRepository.findAll();
}

public Company getCompanyById(Long id){
    return companyRepository.findById(id).orElseThrow(()->
            new ResourceNotFoundException("Company Not Found"));

}

public Company updateCompany(Long id, Company newCompany){
Company existing=companyRepository.findById(id).orElseThrow(()->
        new ResourceNotFoundException("Company Not Found"));

verifyOwnership(existing);

existing.setCompanyName(newCompany.getCompanyName());
    existing.setEmail(newCompany.getEmail());
    existing.setPhoneNumber(newCompany.getPhoneNumber());
    existing.setLocation(newCompany.getLocation());
    existing.setIndustry(newCompany.getIndustry());
    existing.setWebsite(newCompany.getWebsite());

    return companyRepository.save(existing);
}

public void deleteCompany(Long id){
    Company company=companyRepository.findById(id).orElseThrow(()->
            new ResourceNotFoundException("Company Not Found"));
    verifyOwnership(company);
    companyRepository.delete(company);
}

private void verifyOwnership(Company company){
        User currentUser=securityService.getCurrentUser();

        if(currentUser.getRole()==Role.ADMIN){
            return;
        }

        if(!currentUser.getId().equals(company.getUser().getId())){
            throw new AccessDeniedException("You do not have permission to access this company profile");
        }
}

}
