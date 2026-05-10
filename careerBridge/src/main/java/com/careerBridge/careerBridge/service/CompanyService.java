package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Company;
import com.careerBridge.careerBridge.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

public Company saveCompany(Company company){
    return companyRepository.save(company);
}

public List<Company> getAllCompanies(){
    return companyRepository.findAll();
}

public Company getCompanyById(Long id){
    return companyRepository.findById(id).orElse(null);

}

public Company updateCompany(Long id, Company newCompany){
Company existing= getCompanyById(id);

    existing.setCompanyName(newCompany.getCompanyName());
    existing.setEmail(newCompany.getEmail());
    existing.setPhoneNumber(newCompany.getPhoneNumber());
    existing.setLocation(newCompany.getLocation());
    existing.setIndustry(newCompany.getIndustry());
    existing.setWebsite(newCompany.getWebsite());

    return companyRepository.save(existing);
}

public void deleteCompany(Long id){
    companyRepository.deleteById(id);
}
}
